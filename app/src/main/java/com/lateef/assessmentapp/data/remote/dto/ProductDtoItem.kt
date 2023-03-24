package com.lateef.assessmentapp.data.remote.dto

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parceler


@Parcelize
data class ProductDtoItem(
    val id: Int? = null,
    val brand: String?,
    val name: String? = null,
    val price: String? = null,
    val price_sign: String? = null,
    val currency: String? = null,
    val image_link: String? = null,
    val product_link: String? = null,
    val website_link: String?,
    val description: String? = null,
    val rating: Double? = null,
    val category: String? = null,
    val product_type: String? = null,
    val tag_list: List<String>? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val product_api_url: String? = null,
    val api_featured_image: String? = null,
    val product_colors: List<ProductColor>

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        TODO("product_colors")
    ) {
    }

    companion object : Parceler<ProductDtoItem> {

        override fun ProductDtoItem.write(parcel: Parcel, flags: Int) {
            parcel.writeValue(id)
            parcel.writeString(brand)
            parcel.writeString(name)
            parcel.writeString(price)
            parcel.writeString(price_sign)
            parcel.writeString(currency)
            parcel.writeString(image_link)
            parcel.writeString(product_link)
            parcel.writeString(website_link)
            parcel.writeString(description)
            parcel.writeValue(rating)
            parcel.writeString(category)
            parcel.writeString(product_type)
            parcel.writeStringList(tag_list)
            parcel.writeString(created_at)
            parcel.writeString(updated_at)
            parcel.writeString(product_api_url)
            parcel.writeString(api_featured_image)
        }

        override fun create(parcel: Parcel): ProductDtoItem {
            return ProductDtoItem(parcel)
        }
    }
}













