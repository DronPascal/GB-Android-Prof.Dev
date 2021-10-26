package com.rhinemann.homebrewmvp.ui.main

import com.rhinemann.homebrewmvp.core.DataState
import com.rhinemann.homebrewmvp.core.LoadingState
import com.rhinemann.homebrewmvp.data.WordRepository
import com.rhinemann.homebrewmvp.data.remote.WordRetrofitSource
import com.rhinemann.homebrewmvp.domain.IMainInteractor
import com.rhinemann.homebrewmvp.domain.MainInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(
    private val interactor: IMainInteractor = MainInteractor(
        WordRepository(WordRetrofitSource())
    )
) : IMainPresenter {

    private val compositeDisposable = CompositeDisposable()

    private var currentView: MainView? = null

    override fun attachView(view: MainView) {
        if (currentView != view) {
            currentView = view
        }
    }

    override fun detachView(view: MainView) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(interactor.execute(word, isOnline)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { currentView?.renderData(DataState.Loading(LoadingState.Idle)) }
            .subscribe { currentView?.renderData(it) }
        )
    }
}