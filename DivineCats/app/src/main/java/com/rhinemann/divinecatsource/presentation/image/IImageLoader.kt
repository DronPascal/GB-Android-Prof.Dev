package com.rhinemann.divinecatsource.presentation.image

/**
 * Created by dronpascal on 29.10.2021.
 */
interface IImageLoader<T> {

    fun loadTo(url: String, target: T)
}