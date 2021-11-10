package com.rhinemann.divinecatsource.di

import com.rhinemann.divinecatsource.BuildConfig
import com.rhinemann.divinecatsource.core.BuildConfigProvider
import com.rhinemann.divinecatsource.core.CatUrlProvider
import org.koin.dsl.module

/**
 * Created by dronpascal on 30.10.2021.
 */
val config = module {
    single {
        BuildConfigProvider(
            debug = BuildConfig.DEBUG,
            appId = BuildConfig.APPLICATION_ID,
            buildType = BuildConfig.BUILD_TYPE,
            versionCode = BuildConfig.VERSION_CODE,
            versionName = BuildConfig.VERSION_NAME
        )
    }
    single {
        CatUrlProvider(
            baseUrl = BuildConfig.BASE_URL,
            apiKey = BuildConfig.THE_CAT_API_KEY,
            baseImageUrl = "",
            browseMovieBaseUrl = "https://api.thecatapi.com/v1/"
        )
    }
}