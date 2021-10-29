package com.rhinemann.divinecatsource.di

import com.rhinemann.divinecatsource.data.remote.retrofit.ApiHolder
import com.rhinemann.divinecatsource.data.remote.retrofit.IApiHolder
import com.rhinemann.divinecatsource.data.remote.retrofit.cat.CatApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by dronpascal on 28.10.2021.
 */
@Module
interface NetworkModule {

    @Binds
    @Singleton
    fun bindApiHolder(
        impl: ApiHolder,
    ): IApiHolder

    companion object {

        @Provides
        @Singleton
        fun provideCatApi(holder: IApiHolder): CatApi {
            return holder.catApi
        }

    }

}
