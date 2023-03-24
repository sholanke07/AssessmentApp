package com.lateef.assessmentapp.di


import com.lateef.assessmentapp.common.Constants
import com.lateef.assessmentapp.data.remote.ProductApi
import com.lateef.assessmentapp.data.remote.repository.ProductRepositoryImpl
import com.lateef.assessmentapp.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
//it leave as long as the app leave
object AppModule {

    //to monitor http request and response using logcat
    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor) // same for .addInterceptor(...)
        .connectTimeout(30, TimeUnit.SECONDS) //Backend is really slow
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    //we have a single instance of this function
    fun provideProductApi(): ProductApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ProductApi::class.java)
    }


    @Provides
    @Singleton
    fun provideProductRepository(api: ProductApi): ProductRepository {
      return ProductRepositoryImpl(api)
    }
}