package com.rhinemann.divinecatsource.di

import com.rhinemann.divinecatsource.BuildConfig
import com.rhinemann.divinecatsource.core.BuildConfigProvider
import com.rhinemann.divinecatsource.core.CatUrlProvider
import dagger.Module
import dagger.Provides

/**
 * Created by dronpascal on 28.10.2021.
 */
@Module
object ConfigModule {

    @Provides
    fun provideBuildConfigProvider(): BuildConfigProvider {
        return BuildConfigProvider(
            debug = BuildConfig.DEBUG,
            appId = BuildConfig.APPLICATION_ID,
            buildType = BuildConfig.BUILD_TYPE,
            versionCode = BuildConfig.VERSION_CODE,
            versionName = BuildConfig.VERSION_NAME
        )
    }

    @Provides
    fun provideCatUrlProvider(): CatUrlProvider {
        return CatUrlProvider(
            baseUrl = BuildConfig.BASE_URL,
            apiKey = BuildConfig.THE_CAT_API_KEY,
            baseImageUrl = "https://image.tmdb.org/t/p/w300",
            browseMovieBaseUrl = "https://api.thecatapi.com/v1/"
        )
    }

}