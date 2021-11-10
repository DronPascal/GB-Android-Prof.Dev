package com.rhinemann.divinecatsource.presentation.screen.favorite

import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.core.wrappers.UIComponent
import com.rhinemann.divinecatsource.domain.model.cat.Cat

/**
 * Created by dronpascal on 08.10.2021.
 */
sealed class SearchResultState {

    data class Loading(val loadingState: LoadingState) : SearchResultState()

    sealed class Result : SearchResultState() {
        data class Cats(val list: List<Cat>) : SearchResultState.Result()
    }

    data class Response(val uiComponent: UIComponent) : SearchResultState()
}


sealed class SearchResultEvent {

    sealed class OpenScreen : SearchResultEvent() {
        object CatFullscreen : OpenScreen()
    }
}