package com.rhinemann.homebrewmvp.data

import com.rhinemann.homebrewmvp.data.remote.WordModel
import io.reactivex.Observable

/**
 * Created by dronpascal on 22.10.2021.
 */
interface IWordRepository {
    fun getData(word: String): Observable<List<WordModel>>
}