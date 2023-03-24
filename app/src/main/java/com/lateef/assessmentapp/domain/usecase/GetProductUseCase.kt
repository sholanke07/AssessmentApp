package com.lateef.assessmentapp.domain.usecase

import com.lateef.assessmentapp.common.Response
import com.lateef.assessmentapp.data.remote.dto.ProductDto
import com.lateef.assessmentapp.data.remote.dto.ProductDtoItem
import com.lateef.assessmentapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val repository: ProductRepository){

    //this usecase class has a single responsibility to get all product

    //invoke helps to call the usecase as a function
    // flow to emit multiple values over a period of time
    //emitting the state success, error and loading
    operator fun invoke(): Flow<Response<List<ProductDtoItem>>> = flow {
        try {
            emit(Response.Loading())
            val product = repository.getProducts()
            emit(Response.Success(product))

        } catch (e: HttpException){
            //catch exception if http code is not 200
            emit(Response.Error(e.localizedMessage ?: "Error Occur"))

        } catch (e: IOException){
            //catch exception if no internet connection
            emit(Response.Error(e.localizedMessage ?: "No Internet Connection"))
        }
    }

}