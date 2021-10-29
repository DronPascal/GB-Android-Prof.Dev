package com.rhinemann.divinecatsource

import android.app.Application
import com.rhinemann.divinecatsource.di.application
import com.rhinemann.divinecatsource.di.config
import com.rhinemann.divinecatsource.di.mainScreen
import com.rhinemann.divinecatsource.di.network
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
                network,
                application,
                mainScreen
            )
        }
    }
}