package com.rhinemann.divinecatsource.di

import com.rhinemann.divinecatsource.data.CatRepository
import com.rhinemann.divinecatsource.data.ICatRepository
import com.rhinemann.divinecatsource.data.remote.CatRemoteDataSource
import com.rhinemann.divinecatsource.data.remote.ICatRemoteDataSource
import com.rhinemann.divinecatsource.data.remote.retrofit.IApiHolder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by dronpascal on 29.10.2021.
 */
@Module
object DataSourceModule {

    @Provides
    @Singleton
    fun provideCatRemoteDataSource(
        apiHolder: IApiHolder
    ): ICatRemoteDataSource {
        return CatRemoteDataSource(apiHolder.catApi)
    }

    @Provides
    @Singleton
    fun provideCatRepository(
        remoteDataSource: ICatRemoteDataSource
    ): ICatRepository {
        return CatRepository(remoteDataSource)
    }

}