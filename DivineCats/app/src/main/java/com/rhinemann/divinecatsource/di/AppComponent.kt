package com.rhinemann.divinecatsource.di

import android.app.Application
import com.rhinemann.divinecatsource.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by dronpascal on 28.10.2021.
 */
@Singleton
@Component(
    modules = [
        ConfigModule::class,
        GlideModule::class,
        NetworkModule::class,

        ActivityModule::class,
        ViewModelModule::class,
        DataSourceModule::class,
        InteractorModule::class,
        AndroidInjectionModule::class,
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}