package com.rhinemann.divinecatsource.data.remote.retrofit

import com.rhinemann.divinecatsource.core.CatUrlProvider
import com.rhinemann.divinecatsource.data.remote.retrofit.cat.CatApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 * Created by dronpascal on 28.10.2021.
 */
interface IApiHolder {
    val catApi: CatApi
}

class ApiHolder @Inject constructor(
    catUrlProvider: CatUrlProvider,
) : IApiHolder {

    private val client = OkHttpClient.Builder()
        .addInterceptor(QueryInterceptor(hashMapOf("x-api-key" to catUrlProvider.apiKey)))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(catUrlProvider.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override val catApi: CatApi by lazy(LazyThreadSafetyMode.NONE) { retrofit.create(CatApi::class.java) }
}