package com.rhinemann.divinecatsource

import android.app.Application
import com.rhinemann.divinecatsource.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by dronpascal on 28.10.2021.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                config,
                application,
                randcatScreen,
                searchScreen,
                searchResultScreen,
            )
        }
    }
}