package com.rhinemann.divinecatsource.di

import android.app.Application
import android.widget.ImageView
import com.rhinemann.divinecatsource.presentation.image.GlideImageLoader
import com.rhinemann.divinecatsource.presentation.image.IImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by dronpascal on 29.10.2021.
 */
@Module
object GlideModule {

    @Singleton
    @Provides
    fun provideGlideImageLoader(context: Application): IImageLoader<ImageView> {
        return GlideImageLoader<ImageView>(context)
    }

}