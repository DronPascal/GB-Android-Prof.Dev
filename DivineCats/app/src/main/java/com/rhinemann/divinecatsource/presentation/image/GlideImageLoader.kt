package com.rhinemann.divinecatsource.presentation.image

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.IntRange
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


/**
 * Created by dronpascal on 29.10.2021.
 */
class GlideImageLoader(
    private val context: Context
) : IImageLoader<ImageView> {

    override fun loadTo(
        url: String,
        target: ImageView,
        @IntRange(from = -1) height: Int,
        @IntRange(from = -1) width: Int,
        onLoaded: () -> Unit,
    ) {
        Glide
            .with(context)
            .load(url)
            .apply { if (height != -1 && width != -1) override(width, height) }
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    try {
                        onLoaded()
                    } catch (e: Exception) {
                    }
                    return false
                }
            })
            .into(target)
    }

    @SuppressLint("CheckResult")
    override fun loadDrawable(
        url: String,
        circleShape: Boolean,
        cornerRadiusPx: Int,
        @IntRange(from = -1) height: Int,
        @IntRange(from = -1) width: Int,
        setter: (Drawable) -> Unit
    ) {
        Glide
            .with(context)
            .load(url)
            .apply { if (height != -1 && width != -1) override(width, height).centerCrop() }
            .apply { if (circleShape && cornerRadiusPx == 0) circleCrop() }
            .apply {
                if (cornerRadiusPx != 0 && !circleShape)
                    apply(RequestOptions.bitmapTransform(RoundedCorners(cornerRadiusPx)))
            }
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.let { setter(it) }
                    return true
                }
            }).preload()
    }

}