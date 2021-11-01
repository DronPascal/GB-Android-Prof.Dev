package com.gb.stopwatch.data.local.timer

import com.gb.stopwatch.domain.model.StopwatchState

/**
 * Created by dronpascal on 01.11.2021.
 */
interface IStopwatchRepository {
    var currentState: StopwatchState

    fun start()
    fun pause()
    fun stop()

    fun getStringTimeRepresentation(): String
}