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

    private val _events = MutableSharedFlow<SearchResultEvent>()
    val events: LiveData<SearchResultEvent>
        get() = _events.asLiveData(viewModelScope.coroutineContext)

    init {
        // searchPage()
    }

    private fun onCatClicked() {
        SearchResultEvent.OpenScreen.CatFullscreen.let {
            viewModelScope.launch { _events.emit(it) }
        }
    }

}