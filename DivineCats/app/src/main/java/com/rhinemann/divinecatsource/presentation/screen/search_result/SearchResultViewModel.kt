package com.rhinemann.divinecatsource.presentation.screen.search_result

import androidx.lifecycle.*
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.recyclerview.widget.RecyclerView
import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.core.wrappers.UIComponent
import com.rhinemann.divinecatsource.domain.interactor.search_result.IGetSearchPageStream
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Created by dronpascal on 08.10.2021.
 */
class SearchResultViewModel(
    private val getSearchPageStream: IGetSearchPageStream
) : ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>(LoadingState.Idle)
    val loadingState: LiveData<LoadingState> get() = _loadingState

    private val _responseState = MutableLiveData<UIComponent>(UIComponent.None)
    val responseState: LiveData<UIComponent> get() = _responseState

    private val _events = MutableSharedFlow<SearchResultEvent>()
    val events: LiveData<SearchResultEvent>
        get() = _events.asLiveData(viewModelScope.coroutineContext)

    fun searchCats(): Flow<PagingData<Cat>> =
        getSearchPageStream.execute(

        ).cachedIn(viewModelScope)

    private fun onCatClicked() {
        SearchResultEvent.OpenScreen.CatFullscreen.let {
            viewModelScope.launch { _events.emit(it) }
        }
    }
}