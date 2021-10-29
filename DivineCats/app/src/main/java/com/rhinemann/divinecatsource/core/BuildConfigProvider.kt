package com.rhinemann.divinecatsource.core

/**
 * Created by dronpascal on 28.10.2021.
 */
data class BuildConfigProvider(
    val debug: Boolean,
    val appId: String,
    val buildType: String,
    val versionCode: Int,
    val versionName: String,
)