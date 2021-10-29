package com.rhinemann.divinecatsource.di

import com.rhinemann.divinecatsource.data.ICatRepository
import com.rhinemann.divinecatsource.domain.interactor.GetCat
import com.rhinemann.divinecatsource.domain.interactor.IGetCat
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by dronpascal on 28.10.2021.
 */
@Module
object InteractorModule {

    @Provides
    @Singleton
    fun provideGetCat(repository: ICatRepository): IGetCat {
        return GetCat(repository)
    }

}