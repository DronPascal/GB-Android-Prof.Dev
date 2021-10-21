package com.rhinemann.homebrewmvp.data

import com.rhinemann.homebrewmvp.core.DataSource
import com.rhinemann.homebrewmvp.data.remote.IWordRetrofitSource
import com.rhinemann.homebrewmvp.data.remote.WordModel
import io.reactivex.Observable

class WordRepository(
    private val dataSource: IWordRetrofitSource
) : IWordRepository {

    override fun getData(word: String): Observable<List<WordModel>> {
        return dataSource.getData(word)
    }

}