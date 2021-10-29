package com.rhinemann.divinecatsource.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rhinemann.divinecatsource.presentation.MainViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

/**
 * Created by dronpascal on 29.10.2021.
 */
@Module(includes = [InteractorModule::class])
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ViewModelKey(MainViewModel::class)
    @IntoMap
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)