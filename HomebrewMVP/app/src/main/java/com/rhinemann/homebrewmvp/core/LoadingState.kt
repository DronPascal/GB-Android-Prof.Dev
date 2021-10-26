package com.rhinemann.homebrewmvp.core

/**
 * Created by dronpascal on 21.10.2021.
 */
sealed class LoadingState {

    object Loading : LoadingState()

    object Idle : LoadingState()

}