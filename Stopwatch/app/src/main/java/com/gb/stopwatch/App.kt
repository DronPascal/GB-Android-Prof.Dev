package com.gb.stopwatch

import android.app.Application
import com.gb.stopwatch.di.application
import com.gb.stopwatch.di.mainScreen
import org.koin.core.context.startKoin

/**
 * Created by dronpascal on 01.11.2021.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}