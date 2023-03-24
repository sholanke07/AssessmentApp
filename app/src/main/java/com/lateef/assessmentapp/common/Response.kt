package com.lateef.assessmentapp.common


//for the network response class, contain the data and error message
sealed class Response<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Response<T>(data)
    class Error<T>(message: String, data: T? = null) : Response<T>(data, message)
    class Loading<T>(data: T? = null) : Response<T>(data)
}
