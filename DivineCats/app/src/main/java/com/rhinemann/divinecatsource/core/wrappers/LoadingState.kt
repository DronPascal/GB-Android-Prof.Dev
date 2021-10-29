package com.rhinemann.divinecatsource.core.wrappers

/**
 * Created by dronpascal on 28.10.2021.
 */
sealed class LoadingState {

    object Loading : LoadingState()

    object Idle : LoadingState()

}