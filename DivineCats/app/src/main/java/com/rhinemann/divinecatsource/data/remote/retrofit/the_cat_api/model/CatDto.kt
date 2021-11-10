package com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.model

import com.squareup.moshi.Json

data class CatDto(
    @Json(name = "breeds") val breeds: List<BreedDto>,
    @Json(name = "categories") val categories: List<CategoryDto>,
    @Json(name = "id") val id: String,
    @Json(name = "url") val url: String,
    @Json(name = "width") val width: Int,
    @Json(name = "height") val height: Int
)