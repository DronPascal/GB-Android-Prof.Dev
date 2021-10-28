package com.rhinemann.divinecatsource.core.wrappers

/**
 * Created by dronpascal on 28.10.2021.
 */
sealed class DataState<T> {

    // Response can handle error on empty result showing
    data class Response<T>(
        val uiComponent: UIComponent
    ) : DataState<T>()

    // Data always includes expected data
    data class Data<T>(
        val data: T? = null
    ) : DataState<T>()

    // Loading state
    data class Loading<T>(
        val loadingState: LoadingState = LoadingState.Idle
    ) : DataState<T>()

}