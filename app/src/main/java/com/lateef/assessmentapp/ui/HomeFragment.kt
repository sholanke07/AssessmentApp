package com.lateef.assessmentapp.ui

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.lateef.assessmentapp.R
import com.lateef.assessmentapp.common.ProductAdapter
import com.lateef.assessmentapp.data.remote.dto.ProductDtoItem
import com.lateef.assessmentapp.databinding.FragmentHomeBinding
import com.lateef.assessmentapp.ui.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import kotlin.concurrent.thread

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: ProductsViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding!!.root
       // return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = _binding!!.recyclerProduct

        //to check if user network is on
        if (isNetworkAvailable() != true){
            Toast.makeText(requireContext(), "Please check your internet connection", Toast.LENGTH_LONG).show()
            return
        }



        callApi()


    }

    private fun callApi(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                viewModel._state.collect {
                    when {
                        it.isLoading -> {
                                _binding!!.progressBar.visibility = View.VISIBLE

                        }
                        it.error.isNotBlank() -> {

                                _binding!!.progressBar.visibility = View.GONE

                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                            Timber.d(it.error)
                        }
                        it.product.isNotEmpty() -> {
                                _binding!!.progressBar.visibility = View.GONE


                            var list = it.product as ArrayList<ProductDtoItem>
                            adapter = ProductAdapter(requireContext(), list)
                            recyclerView.layoutManager = LinearLayoutManager(requireContext())
                            val divider = DividerItemDecoration(
                                requireContext(),
                                DividerItemDecoration.VERTICAL
                            )
                            recyclerView.addItemDecoration(divider)

                            recyclerView.adapter = adapter

                            adapter.setData(list)
                            Timber.d("$list")

                        }
                    }

                }
            }catch (e: IOException){
                Toast.makeText(requireContext(), e.localizedMessage ?: "No Internet Connection", Toast.LENGTH_LONG).show()

            }

        }

    }

    private fun isNetworkAvailable(): Boolean{
        val connectivityManager = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }
}