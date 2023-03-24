package com.lateef.assessmentapp.domain.repository

import com.lateef.assessmentapp.data.remote.dto.ProductDto
import com.lateef.assessmentapp.data.remote.dto.ProductDtoItem

interface ProductRepository {

   //define the function for the repository
   suspend fun getProducts(): List<ProductDtoItem>


}