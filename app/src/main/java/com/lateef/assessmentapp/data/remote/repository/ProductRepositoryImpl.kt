package com.lateef.assessmentapp.data.remote.repository

import com.lateef.assessmentapp.data.remote.ProductApi
import com.lateef.assessmentapp.data.remote.dto.ProductDto
import com.lateef.assessmentapp.data.remote.dto.ProductDtoItem
import com.lateef.assessmentapp.domain.repository.ProductRepository
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(private val api: ProductApi): ProductRepository {


    override suspend fun getProducts(): List<ProductDtoItem> {
        return api.getProducts()
    }
}