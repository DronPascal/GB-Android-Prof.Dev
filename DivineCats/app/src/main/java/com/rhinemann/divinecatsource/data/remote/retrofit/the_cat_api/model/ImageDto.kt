package com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.model

import com.squareup.moshi.Json

data class ImageDto(
    @Json(name = "height") val height: Int,
    @Json(name = "id") val id: String,
    @Json(name = "url") val url: String,
    @Json(name = "width") val width: Int
)