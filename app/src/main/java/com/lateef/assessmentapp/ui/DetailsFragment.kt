package com.lateef.assessmentapp.ui

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.lateef.assessmentapp.R
import com.lateef.assessmentapp.data.remote.dto.ProductDtoItem
import com.lateef.assessmentapp.databinding.FragmentDetailsBinding
import com.lateef.assessmentapp.databinding.FragmentHomeBinding
import kotlin.system.exitProcess


class DetailsFragment() : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()
    private var _binding: FragmentDetailsBinding? = null
    var web_link: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return _binding!!.root
        //return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        _binding!!.brandName.setText(args.product2.brand)
        _binding!!.brandDiscription.setText(args.product2.description)
        _binding!!.dateTxt.setText(args.product2.created_at)
        _binding!!.typeTxt.setText(args.product2.product_type)
        _binding!!.nameTxt.setText(args.product2.name)

        Glide.with(this)
            .load(args.product2.image_link)
            .centerInside()
            //.centerCrop()
            .into(_binding!!.productImage)


        _binding!!.priceLayout.setOnClickListener {
            _binding!!.priceP.setText("$ " + args.product2.price)
        }

        _binding!!.webLayout.setOnClickListener {

             web_link = args.product2.website_link

            // convert String? to String
            web_link?.let {
                val action = DetailsFragmentDirections.actionDetailsFragmentToWebFragment(it)
                findNavController().navigate(action)
            }
        }

        _binding!!.categoryLayout.setOnClickListener {
            _binding!!.categoryTxt.setText(args.product2.category)

        }
        _binding!!.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
        }

        activity?.onBackPressedDispatcher?.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(
                true
            ) {
                override fun handleOnBackPressed() {

                    findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
                }
            })



    }

}
