package com.rhinemann.homebrewmvp.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WordApiService {

    @GET("words/search")
    fun search(
        @Query("search") word: String
    ): Observable<List<WordModel>>

}