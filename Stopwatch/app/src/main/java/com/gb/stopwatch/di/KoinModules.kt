package com.gb.stopwatch.di

import com.gb.stopwatch.data.local.timer.IStopwatchRepository
import com.gb.stopwatch.data.local.timer.ITimestampDataSource
import com.gb.stopwatch.data.local.timer.StopwatchRepository
import com.gb.stopwatch.data.local.timer.TimestampDataSource
import com.gb.stopwatch.data.local.timer.helper.ElapsedTimeCalculator
import com.gb.stopwatch.data.local.timer.helper.StopwatchStateCalculator
import com.gb.stopwatch.domain.entity.TimestampMillisecondsFormatter
import com.gb.stopwatch.presentation.StopwatchListViewModel
import org.koin.dsl.module

/**
 * Created by dronpascal on 01.11.2021.
 */
val application = module {
    single<ITimestampDataSource> { TimestampDataSource() }
    single { ElapsedTimeCalculator(get()) }
    single { StopwatchStateCalculator(get(), get()) }
    single { TimestampMillisecondsFormatter() }
    single<IStopwatchRepository> { StopwatchRepository(get(), get(), get()) }
}

val mainScreen = module {
    factory { StopwatchListViewModel(get()) }
}