package com.lateef.assessmentapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lateef.assessmentapp.R
import com.lateef.assessmentapp.databinding.FragmentDetailsBinding
import com.lateef.assessmentapp.databinding.FragmentWebBinding


class WebFragment : Fragment() {

    private var _binding: FragmentWebBinding? = null
    private val args by navArgs<WebFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        return _binding!!.root
       // return inflater.inflate(R.layout.fragment_web, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //receiving the web link and displaying the website
        val link = args.link
        _binding!!.webView.apply {
            webViewClient = WebViewClient()
            link.let { loadUrl(it) }

        }

        activity?.onBackPressedDispatcher?.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(
                true
            ) {
                override fun handleOnBackPressed() {

                    findNavController().navigate(R.id.action_webFragment_to_homeFragment)
                }
            })

    }
}