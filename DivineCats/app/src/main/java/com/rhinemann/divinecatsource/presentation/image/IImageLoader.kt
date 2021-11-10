package com.rhinemann.divinecatsource.presentation.image

import android.graphics.drawable.Drawable
import androidx.annotation.IntRange

/**
 * Created by dronpascal on 29.10.2021.
 */
interface IImageLoader<T> {

    fun loadTo(
        url: String,
        target: T,
        @IntRange(from = -1) height: Int = -1,
        @IntRange(from = -1) width: Int = -1,
        onLoaded: () -> Unit = {}
    )

    fun loadDrawable(
        url: String,
        circleShape: Boolean = false,
        cornerRadiusPx: Int = 0,
        @IntRange(from = -1) height: Int = -1,
        @IntRange(from = -1) width: Int = -1,
        onLoaded: (Drawable) -> Unit
    )
}