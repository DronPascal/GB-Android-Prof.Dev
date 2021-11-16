package com.rhinemann.divinecatsource.presentation.screen.search_result

import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.core.wrappers.UIComponent
import com.rhinemann.divinecatsource.domain.model.cat.Cat

/**
 * Created by dronpascal on 08.10.2021.
 */
sealed class SearchResultEvent {

    sealed class OpenScreen : SearchResultEvent() {
        object CatFullscreen : OpenScreen()
    }
}