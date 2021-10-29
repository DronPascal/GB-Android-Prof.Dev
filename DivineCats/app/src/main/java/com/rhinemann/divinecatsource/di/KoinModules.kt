package com.rhinemann.divinecatsource.di

import android.widget.ImageView
import com.rhinemann.divinecatsource.BuildConfig
import com.rhinemann.divinecatsource.core.BuildConfigProvider
import com.rhinemann.divinecatsource.core.CatUrlProvider
import com.rhinemann.divinecatsource.data.CatRepository
import com.rhinemann.divinecatsource.data.ICatRepository
import com.rhinemann.divinecatsource.data.remote.CatRemoteDataSource
import com.rhinemann.divinecatsource.data.remote.ICatRemoteDataSource
import com.rhinemann.divinecatsource.data.remote.retrofit.ApiHolder
import com.rhinemann.divinecatsource.data.remote.retrofit.cat.CatApi
import com.rhinemann.divinecatsource.domain.interactor.GetCat
import com.rhinemann.divinecatsource.domain.interactor.IGetCat
import com.rhinemann.divinecatsource.presentation.MainViewModel
import com.rhinemann.divinecatsource.presentation.image.GlideImageLoader
import com.rhinemann.divinecatsource.presentation.image.IImageLoader
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by dronpascal on 29.10.2021.
 */
val network = module {
    // how? single<IApiHolder> { ApiHolder(get()) }
    single<CatApi> { ApiHolder(get()).catApi }
}

val application = module {
    single<IImageLoader<ImageView>> { GlideImageLoader<ImageView>(androidContext()) }
    single<ICatRemoteDataSource> { CatRemoteDataSource(get()) }
    single<ICatRepository> { CatRepository(get()) }
}

val mainScreen = module {
    factory<IGetCat> { GetCat(get()) }
    factory { MainViewModel(get()) }
}