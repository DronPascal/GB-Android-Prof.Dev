package com.rhinemann.homebrewmvp.ui.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.rhinemann.homebrewmvp.R
import com.rhinemann.homebrewmvp.core.DataState
import com.rhinemann.homebrewmvp.core.LoadingState
import com.rhinemann.homebrewmvp.core.UiComponent
import com.rhinemann.homebrewmvp.data.remote.WordModel
import com.rhinemann.homebrewmvp.ui.base.BaseActivity

class MainActivity : BaseActivity<DataState>() {

    private lateinit var binding: AcMainBinding

    private var adapter: MainAdapter? = null

    override fun createPresenter(): IMainPresenter {
        return MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    presenter.getData(searchWord, true)
                }
            })
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

        binding.mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        adapter = MainAdapter { }
        binding.mainActivityRecyclerview.adapter = adapter
    }

    override fun renderData(appState: DataState<List<WordModel>>) {
        when (appState) {
            is DataState.Data -> {
                val dataModel = appState.data
                if (dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    adapter!!.submitList(dataModel)
                }
            }
            is DataState.Loading -> {
                showViewLoading()
                if (appState.loadingState is LoadingState.Loading) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is DataState.Response -> {
                showErrorScreen(appState.uiComponent)
            }
        }
    }

    private fun showErrorScreen(error: UiComponent) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            presenter.getData("hi", true)
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }

    companion object {

        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}