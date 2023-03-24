package com.lateef.assessmentapp.common

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lateef.assessmentapp.R
import com.lateef.assessmentapp.data.remote.dto.ProductDtoItem
import com.lateef.assessmentapp.ui.HomeFragmentDirections
import java.util.*
import kotlin.collections.ArrayList

class ProductAdapter(private val context: Context, var itemList: ArrayList<ProductDtoItem>)
    : RecyclerView.Adapter<ProductAdapter.CharacterListViewHolder>(){



    inner class CharacterListViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val productName: TextView = view.findViewById(R.id.com_name_list)
        val thumbnail: ImageView = view.findViewById(R.id.product_image)
        val brandName: TextView = view.findViewById(R.id.phone_txt)
        val cardCharacter: LinearLayout = view.findViewById(R.id.productLinearLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_product, parent, false)
        return CharacterListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val list = itemList[position]
        holder.productName.text = list.brand
        holder.brandName.text = list.name

        Glide.with(context)
            .load(list.image_link)
            .into(holder.thumbnail)

        holder.cardCharacter.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(list)
            holder.view.findNavController().navigate(action)

        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(productList: ArrayList<ProductDtoItem>){
        this.itemList = productList

        // to sort by brand Alphabetical order
        productList.sortBy {
            it.brand
        }
        notifyDataSetChanged()
    }


}