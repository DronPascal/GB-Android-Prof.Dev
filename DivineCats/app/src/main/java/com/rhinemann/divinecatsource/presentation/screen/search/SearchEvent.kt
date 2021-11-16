package com.rhinemann.divinecatsource.presentation.screen.search

import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.core.wrappers.UIComponent
import com.rhinemann.divinecatsource.domain.model.cat.Breed
import com.rhinemann.divinecatsource.domain.model.cat.Category

/**
 * Created by dronpascal on 07.11.2021.
 */
sealed class SearchEvent {

    sealed class OpenScreen : SearchEvent() {
        object SearchResult : OpenScreen()
    }
}