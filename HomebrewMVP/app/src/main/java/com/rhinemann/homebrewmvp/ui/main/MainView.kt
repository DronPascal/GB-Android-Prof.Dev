package com.rhinemann.homebrewmvp.ui.main

import com.rhinemann.homebrewmvp.core.DataState
import com.rhinemann.homebrewmvp.core.MVPContract
import com.rhinemann.homebrewmvp.data.remote.WordModel

/**
 * Created by dronpascal on 22.10.2021.
 */
interface MainView: MVPContract.View {
    fun renderData(dataState: DataState<List<WordModel>>)
}