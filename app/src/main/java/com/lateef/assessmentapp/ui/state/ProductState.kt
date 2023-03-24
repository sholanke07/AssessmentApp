package com.lateef.assessmentapp.ui.state

import com.lateef.assessmentapp.data.remote.dto.ProductDto
import com.lateef.assessmentapp.data.remote.dto.ProductDtoItem


data class ProductState(
    val isLoading: Boolean = false,
    val product: List<ProductDtoItem> = emptyList(),
    val error: String = ""
)
