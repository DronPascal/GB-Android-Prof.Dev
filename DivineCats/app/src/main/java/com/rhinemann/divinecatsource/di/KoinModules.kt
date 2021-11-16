package com.rhinemann.divinecatsource.di

import android.widget.ImageView
import com.rhinemann.divinecatsource.data.CatRepository
import com.rhinemann.divinecatsource.data.ICatRepository
import com.rhinemann.divinecatsource.data.local.cache.CatLocalDataSource
import com.rhinemann.divinecatsource.data.local.cache.ICatLocalDataSource
import com.rhinemann.divinecatsource.data.local.cache.room.CatDatabase
import com.rhinemann.divinecatsource.data.remote.CatRemoteDataSource
import com.rhinemann.divinecatsource.data.remote.ICatRemoteDataSource
import com.rhinemann.divinecatsource.data.remote.retrofit.ApiHolder
import com.rhinemann.divinecatsource.data.remote.retrofit.IApiHolder
import com.rhinemann.divinecatsource.domain.interactor.random.AddCatToFavorite
import com.rhinemann.divinecatsource.domain.interactor.random.GetRandomCat
import com.rhinemann.divinecatsource.domain.interactor.random.IAddCatToFavorite
import com.rhinemann.divinecatsource.domain.interactor.random.IGetRandomCat
import com.rhinemann.divinecatsource.domain.interactor.search.*
import com.rhinemann.divinecatsource.domain.interactor.search_result.GetSearchPageStream
import com.rhinemann.divinecatsource.domain.interactor.search_result.IGetSearchPageStream
import com.rhinemann.divinecatsource.presentation.MainActivity
import com.rhinemann.divinecatsource.presentation.MainViewModel
import com.rhinemann.divinecatsource.presentation.image.GlideImageLoader
import com.rhinemann.divinecatsource.presentation.image.IImageLoader
import com.rhinemann.divinecatsource.presentation.navigation.INavigator
import com.rhinemann.divinecatsource.presentation.navigation.Navigator
import com.rhinemann.divinecatsource.presentation.screen.randomcat.RandcatViewModel
import com.rhinemann.divinecatsource.presentation.screen.search.SearchViewModel
import com.rhinemann.divinecatsource.presentation.screen.search_result.SearchResultViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by dronpascal on 29.10.2021.
 */
val application = module {
    single<IApiHolder> { ApiHolder(get()) }
    single { get<IApiHolder>().theCatApi }
    single<ICatRemoteDataSource> { CatRemoteDataSource(get()) }

    single { CatDatabase.create(androidContext()) }
    single<ICatLocalDataSource> { CatLocalDataSource(get<CatDatabase>().catDao) }

    single<ICatRepository> { CatRepository(get(), get()) }

    single<IImageLoader<ImageView>> { GlideImageLoader(androidContext()) }
    scope<MainActivity> {
        factory<INavigator> { param -> Navigator(param.get()) }
    }
    viewModel { MainViewModel() }
}

val randcatScreen = module {
    factory<IGetRandomCat> { GetRandomCat(get()) }
    factory<IAddCatToFavorite> { AddCatToFavorite(get()) }
    viewModel { RandcatViewModel(get(), get()) }
}

val searchScreen = module {
    factory<IGetAllCategories> { GetAllCategories(get()) }
    factory<IGetAllBreeds> { GetAllBreeds(get()) }
    viewModel { SearchViewModel(get(), get()) }
}

val searchResultScreen = module {
    factory<IGetSearchPage> { GetSearchPage(get()) }
    factory<IGetSearchPageStream> { GetSearchPageStream(get()) }
    viewModel { SearchResultViewModel(get()) }
}