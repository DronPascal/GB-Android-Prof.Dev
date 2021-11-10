package com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.model

import com.squareup.moshi.Json

data class WeightDto(
    @Json(name = "imperial") val imperial: String,
    @Json(name = "metric") val metric: String
)