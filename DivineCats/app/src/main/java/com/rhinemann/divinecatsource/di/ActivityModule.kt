package com.rhinemann.divinecatsource.di

import com.rhinemann.divinecatsource.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by dronpascal on 29.10.2021.
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}