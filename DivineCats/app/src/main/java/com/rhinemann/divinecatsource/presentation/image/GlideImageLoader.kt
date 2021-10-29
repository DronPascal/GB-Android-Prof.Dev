package com.rhinemann.divinecatsource.presentation.image

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by dronpascal on 29.10.2021.
 */
class GlideImageLoader<T>(
    private val context: Context
) : IImageLoader<ImageView> {

    override fun loadTo(
        url: String,
        target: ImageView,
    ) {
        Glide
            .with(context)
            .load(url)
            .into(target)
    }
}