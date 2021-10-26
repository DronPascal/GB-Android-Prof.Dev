package com.rhinemann.homebrewmvp.core

/**
 * Created by dronpascal on 21.10.2021.
 */
sealed class DataState<T> {

    data class Response<T>(
        val uiComponent: UiComponent
    ) : DataState<T>()

    data class Data<T>(
        val data: T? = null
    ) : DataState<T>()

    data class Loading<T>(
        val loadingState: LoadingState = LoadingState.Idle
    ) : DataState<T>()

}