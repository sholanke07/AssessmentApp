package com.lateef.assessmentapp.data.remote

import com.lateef.assessmentapp.data.remote.dto.ProductDto
import com.lateef.assessmentapp.data.remote.dto.ProductDtoItem
import retrofit2.http.GET

interface ProductApi {

    //definding the endpoint
    @GET("/api/v1/products.json")
    suspend fun getProducts(): List<ProductDtoItem>
}