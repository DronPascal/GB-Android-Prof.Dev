package com.rhinemann.homebrewmvp.ui.main

import com.rhinemann.homebrewmvp.core.MVPContract

/**
 * Created by dronpascal on 22.10.2021.
 */
interface IMainPresenter: MVPContract.Presenter<MainView> {
    fun getData(word: String, isOnline: Boolean)
}