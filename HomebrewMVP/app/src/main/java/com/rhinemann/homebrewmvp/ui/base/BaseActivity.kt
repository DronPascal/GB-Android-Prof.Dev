package com.rhinemann.homebrewmvp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rhinemann.homebrewmvp.core.DataState
import com.rhinemann.homebrewmvp.core.MVPContract

abstract class BaseActivity<T : Any>
    : AppCompatActivity(), MVPContract.View {

    protected lateinit var presenter
            : MVPContract.Presenter<MVPContract.View>

    protected abstract fun createPresenter()
            : MVPContract.Presenter<MVPContract.View>

    abstract fun renderData(dataState: DataState<T>)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}