package com.rhinemann.divinecatsource.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.domain.interactor.IGetCat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Created by dronpascal on 28.10.2021.
 */
class MainViewModel(
    private val getCat: IGetCat
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState: LiveData<MainViewState>
        get() = _viewState.asLiveData(viewModelScope.coroutineContext)

    init {
        getCat()
    }

    fun getCat() {
        getCat.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    // error toast or smth
                }
                is DataState.Data -> {
                    _viewState.value = _viewState.value.copy(cat = dataState.data)
                }
                is DataState.Loading -> {
                    _viewState.value = _viewState.value.copy(loadingState = dataState.loadingState)
                }
            }
        }.launchIn(viewModelScope)
    }
}