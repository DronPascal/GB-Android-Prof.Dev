package com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.model

import com.squareup.moshi.Json

data class CategoryDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)