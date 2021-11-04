package com.gb.stopwatch.data.local.timer.helper

import com.gb.stopwatch.data.local.timer.ITimestampDataSource
import com.gb.stopwatch.domain.model.StopwatchState

/**
 * Created by dronpascal on 01.11.2021.
 */
class StopwatchStateCalculator(
    private val timestampDataSource: ITimestampDataSource,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
) {

    fun calculateRunningState(oldState: StopwatchState): StopwatchState.Running =
        when (oldState) {
            is StopwatchState.Running -> oldState
            is StopwatchState.Paused -> {
                StopwatchState.Running(
                    startTime = timestampDataSource.getMilliseconds(),
                    elapsedTime = oldState.elapsedTime
                )
            }
        }

    fun calculatePausedState(oldState: StopwatchState): StopwatchState.Paused =
        when (oldState) {
            is StopwatchState.Running -> {
                val elapsedTime = elapsedTimeCalculator.calculate(oldState)
                StopwatchState.Paused(elapsedTime = elapsedTime)
            }
            is StopwatchState.Paused -> oldState
        }
}

