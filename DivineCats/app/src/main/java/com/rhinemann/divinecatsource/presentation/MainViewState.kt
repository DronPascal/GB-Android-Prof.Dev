package com.rhinemann.divinecatsource.presentation

import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.domain.model.Cat

/**
 * Created by dronpascal on 29.10.2021.
 */
data class MainViewState(
    val loadingState: LoadingState = LoadingState.Idle,
    var cat: Cat? = null,
)
