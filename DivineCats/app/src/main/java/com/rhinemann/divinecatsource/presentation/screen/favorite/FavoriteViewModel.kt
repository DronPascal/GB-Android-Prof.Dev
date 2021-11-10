package com.rhinemann.divinecatsource.presentation.screen.favorite

import androidx.lifecycle.*
import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.domain.interactor.search.IGetSearchPage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Created by dronpascal on 08.10.2021.
 */
class FavoriteViewModel(private val getSearchPage: IGetSearchPage) : ViewModel() {

    private val _state =
        MutableLiveData<SearchResultState>(SearchResultState.Loading(LoadingState.Idle))
    val state: LiveData<SearchResultState> get() = _state

    private val _events = MutableSharedFlow<SearchResultEvent>()
    val events: LiveData<SearchResultEvent>
        get() = _events.asLiveData(viewModelScope.coroutineContext)

    init {
        // searchPage()
    }

    private fun searchPage() {
        getSearchPage.execute(20, 0).onEach { dataState ->
            when (dataState) {
                is DataState.Response -> _state.value =
                    SearchResultState.Response(dataState.uiComponent)
                is DataState.Data -> _state.value =
                    dataState.data?.let { SearchResultState.Result.Cats(it) }
                is DataState.Loading -> _state.value =
                    SearchResultState.Loading(dataState.loadingState)
            }
        }.launchIn(viewModelScope)
    }

    private fun onCatClicked() {
        SearchResultEvent.OpenScreen.CatFullscreen.let {
            viewModelScope.launch { _events.emit(it) }
        }
    }

}