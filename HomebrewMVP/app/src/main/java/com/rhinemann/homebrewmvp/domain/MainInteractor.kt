package com.rhinemann.homebrewmvp.domain

import com.rhinemann.homebrewmvp.core.DataState
import com.rhinemann.homebrewmvp.data.IWordRepository
import com.rhinemann.homebrewmvp.data.remote.WordModel
import io.reactivex.Observable

class MainInteractor(
    private val repository: IWordRepository,
) : IMainInteractor {

    override fun execute(
        word: String,
        isRemoteSource: Boolean
    ): Observable<DataState<List<WordModel>>> {
        return repository.getData(word)
            .map {
                DataState.Data<List<WordModel>>(it)
            }
    }
}