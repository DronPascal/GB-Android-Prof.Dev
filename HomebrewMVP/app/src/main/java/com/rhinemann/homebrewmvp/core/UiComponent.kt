package com.rhinemann.homebrewmvp.core

/**
 * Created by dronpascal on 21.10.2021.
 */
sealed class UiComponent {

    data class Toast(
        val message: String,
    ) : UiComponent()

    object None: UiComponent()

}