package com.rhinemann.homebrewmvp.data.remote

import io.reactivex.Observable

/**
 * Created by dronpascal on 22.10.2021.
 */
interface IWordRetrofitSource {
    fun getData(word: String): Observable<List<WordModel>>
}