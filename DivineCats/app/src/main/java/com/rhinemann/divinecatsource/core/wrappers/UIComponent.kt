package com.rhinemann.divinecatsource.core.wrappers

/**
 * Created by dronpascal on 28.10.2021.
 */
sealed class UIComponent {

    data class Toast(
        val message: String,
    ) : UIComponent()

    data class Dialog(
        val title: String,
        val message: String,
    ) : UIComponent()

    object None : UIComponent()

}