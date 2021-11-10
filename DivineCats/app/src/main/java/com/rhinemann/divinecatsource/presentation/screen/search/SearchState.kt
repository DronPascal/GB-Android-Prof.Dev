package com.rhinemann.divinecatsource.presentation.screen.search

import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.core.wrappers.UIComponent
import com.rhinemann.divinecatsource.domain.model.cat.Breed
import com.rhinemann.divinecatsource.domain.model.cat.Category

/**
 * Created by dronpascal on 07.11.2021.
 */
sealed class SearchState {

    data class Loading(val loadingState: LoadingState) : SearchState()

    sealed class Result : SearchState() {
        data class Categories(val list: List<Category>) : Result()
        data class Breeds(val list: List<Breed>) : Result()
    }

    data class Response(val uiComponent: UIComponent) : SearchState()
}


sealed class SearchEvent {

    sealed class OpenScreen : SearchEvent() {
        object SearchResult : OpenScreen()
    }
}