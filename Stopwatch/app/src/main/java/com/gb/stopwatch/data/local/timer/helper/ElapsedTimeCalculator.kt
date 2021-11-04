package com.gb.stopwatch.data.local.timer.helper

import com.gb.stopwatch.data.local.timer.ITimestampDataSource
import com.gb.stopwatch.domain.model.StopwatchState

/**
 * Created by dronpascal on 01.11.2021.
 */
class ElapsedTimeCalculator(
    private val timestampDataSource: ITimestampDataSource,
) {

    fun calculate(state: StopwatchState.Running): Long {
        val currentTimestamp = timestampDataSource.getMilliseconds()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }
        return timePassedSinceStart + state.elapsedTime
    }
}