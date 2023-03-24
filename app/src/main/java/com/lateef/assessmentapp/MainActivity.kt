package com.lateef.assessmentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lateef.assessmentapp.databinding.ActivityMainBinding
import com.lateef.assessmentapp.ui.HomeFragment
import com.lateef.assessmentapp.ui.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.http.HttpMethod

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.activity_main)

//        val homeFragment = HomeFragment()
//        val fragmentManager: FragmentManager = supportFragmentManager
//        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragmentz_main, homeFragment).commit()
    }
}