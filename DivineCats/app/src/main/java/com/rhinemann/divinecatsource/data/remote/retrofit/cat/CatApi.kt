package com.rhinemann.divinecatsource.data.remote.retrofit.cat

import retrofit2.http.GET

/**
 * Created by dronpascal on 28.10.2021.
 */
interface CatApi {

    @GET("images/search")
    suspend fun getCat(): List<CatDto>

}