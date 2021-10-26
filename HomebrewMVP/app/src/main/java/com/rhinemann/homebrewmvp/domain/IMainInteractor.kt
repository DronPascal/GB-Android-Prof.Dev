package com.rhinemann.homebrewmvp.domain

import com.rhinemann.homebrewmvp.core.DataState
import com.rhinemann.homebrewmvp.core.Interactor
import com.rhinemann.homebrewmvp.data.remote.WordModel
import io.reactivex.Observable

/**
 * Created by dronpascal on 22.10.2021.
 */
interface IMainInteractor : Interactor {
    fun execute(word: String, isRemoteSource: Boolean)
            : Observable<DataState<List<WordModel>>>
}