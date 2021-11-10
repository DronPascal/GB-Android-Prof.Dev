package com.rhinemann.divinecatsource.data.remote.retrofit

import com.rhinemann.divinecatsource.core.CatUrlProvider
import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.TheCatApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by dronpascal on 28.10.2021.
 */
interface IApiHolder {
    val theCatApi: TheCatApi
}

class ApiHolder(
    catUrlProvider: CatUrlProvider,
) : IApiHolder {

    private val client = OkHttpClient.Builder()
        .addInterceptor(QueryInterceptor(hashMapOf("x-api-key" to catUrlProvider.apiKey)))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(catUrlProvider.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    override val theCatApi: TheCatApi by lazy(LazyThreadSafetyMode.NONE) { retrofit.create(TheCatApi::class.java) }
}