package com.rhinemann.divinecatsource.presentation.screen.randomcat

import androidx.lifecycle.*
import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.core.wrappers.UIComponent
import com.rhinemann.divinecatsource.domain.interactor.random.IAddCatToFavorite
import com.rhinemann.divinecatsource.domain.interactor.random.IGetRandomCat
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Created by dronpascal on 04.11.2021.
 */
class RandcatViewModel(
    private val getRandomCat: IGetRandomCat,
    private val addCatToFavorite: IAddCatToFavorite,
) : ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>(LoadingState.Idle)
    val loadingState: LiveData<LoadingState> get() = _loadingState

    private val _responseState = MutableLiveData<UIComponent>(UIComponent.None)
    val responseState: LiveData<UIComponent> get() = _responseState

    private val _currentCat = MutableLiveData<Cat>(null)
    val currentCat: LiveData<Cat> get() = _currentCat

    private val _saveBtnState = MutableLiveData<SaveBtnState>(SaveBtnState.Idle)
    val saveBtnState: LiveData<SaveBtnState> get() = _saveBtnState

    private val _events = MutableSharedFlow<RandcatEvent>()
    val events: LiveData<RandcatEvent>
        get() = _events.asLiveData(viewModelScope.coroutineContext)

    init {
        getNextCat()
    }

    fun getNextCat() {
        _saveBtnState.value = SaveBtnState.Idle
        getRandomCat.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> _responseState.value = dataState.uiComponent
                is DataState.Data -> dataState.data?.let { _currentCat.value = it }
                is DataState.Loading -> _loadingState.value = dataState.loadingState
            }
        }.launchIn(viewModelScope)
    }

    fun addCatToFavorite() {
        if (_saveBtnState.value is SaveBtnState.Saved) return
        _currentCat.value?.let {
            addCatToFavorite.execute(it).onEach { dataState ->
                when (dataState) {
                    is DataState.Response -> _responseState.value = dataState.uiComponent
                    is DataState.Data -> _saveBtnState.value = SaveBtnState.Saved
                    is DataState.Loading -> _loadingState.value = dataState.loadingState
                }
            }.launchIn(viewModelScope)
        }
    }
}