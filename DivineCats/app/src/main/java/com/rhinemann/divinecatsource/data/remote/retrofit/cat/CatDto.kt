package com.rhinemann.divinecatsource.data.remote.retrofit.cat

data class CatDto(
    val breeds: List<Any>,
    val categories: List<Any>,
    val id: String,
    val url: String
)