package com.rhinemann.divinecatsource.presentation.screen.search

import androidx.lifecycle.*
import com.rhinemann.divinecatsource.core.util.invalidate
import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.core.wrappers.UIComponent
import com.rhinemann.divinecatsource.domain.interactor.search.IGetAllBreeds
import com.rhinemann.divinecatsource.domain.interactor.search.IGetAllCategories
import com.rhinemann.divinecatsource.presentation.screen.search.model.CheckableBreed
import com.rhinemann.divinecatsource.presentation.screen.search.model.CheckableBreed.Companion.toCheckable
import com.rhinemann.divinecatsource.presentation.screen.search.model.CheckableCategory
import com.rhinemann.divinecatsource.presentation.screen.search.model.CheckableCategory.Companion.toCheckable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Created by dronpascal on 07.10.2021.
 */
class SearchViewModel(
    private val getAllCategories: IGetAllCategories,
    private val getAllBreeds: IGetAllBreeds,
) : ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>(LoadingState.Idle)
    val loadingState: LiveData<LoadingState> get() = _loadingState

    private val _responseState = MutableLiveData<UIComponent>(UIComponent.None)
    val responseState: LiveData<UIComponent> get() = _responseState

    private val _categoriesState = MutableLiveData<List<CheckableCategory>>(emptyList())
    val categoriesState: LiveData<List<CheckableCategory>> get() = _categoriesState

    private val _breedsState = MutableLiveData<List<CheckableBreed>>(emptyList())
    val breedsState: LiveData<List<CheckableBreed>> get() = _breedsState

    private val _events = MutableSharedFlow<SearchEvent>()
    val events: LiveData<SearchEvent>
        get() = _events.asLiveData(viewModelScope.coroutineContext)

    init {
        getAllCategories()
        getAllBreeds()
    }

    private fun getAllCategories() {
        getAllCategories.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> _responseState.value = dataState.uiComponent
                is DataState.Data -> _categoriesState.value =
                    dataState.data?.map { it.toCheckable() }
                is DataState.Loading -> _loadingState.value = dataState.loadingState
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllBreeds() {
        getAllBreeds.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> _responseState.value = dataState.uiComponent
                is DataState.Data -> _breedsState.value =
                    dataState.data?.map { it.toCheckable() }
                is DataState.Loading -> _loadingState.value = dataState.loadingState
            }
        }.launchIn(viewModelScope)
    }

    fun onCategoryCheckChanged(name: String) {
        _categoriesState.value?.first { it.name == name }?.apply { isChecked = !isChecked }
    }

    fun onBreedCheckChanged(name: String) {
        _breedsState.value?.find { it.isChecked && it.name != name }
            ?.apply { isChecked = false }  // single selection
        _breedsState.value?.first { it.name == name }?.apply { isChecked = !isChecked }
        _breedsState.invalidate()
    }

    fun onWatchCatsClicked() {
        SearchEvent.OpenScreen.SearchResult.let {
            viewModelScope.launch { _events.emit(it) }
        }
    }
}