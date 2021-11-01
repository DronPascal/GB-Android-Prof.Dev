package com.gb.stopwatch.data.local.timer

import com.gb.stopwatch.data.local.timer.helper.ElapsedTimeCalculator
import com.gb.stopwatch.data.local.timer.helper.StopwatchStateCalculator
import com.gb.stopwatch.domain.model.StopwatchState
import com.gb.stopwatch.domain.entity.TimestampMillisecondsFormatter

/**
 * Created by dronpascal on 01.11.2021.
 */
class StopwatchRepository(
    private val stopwatchStateCalculator: StopwatchStateCalculator,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
    private val timestampMillisecondsFormatter: TimestampMillisecondsFormatter,
) : IStopwatchRepository {

    override var currentState: StopwatchState = StopwatchState.Paused(0)

    override fun start() {
        currentState = stopwatchStateCalculator.calculateRunningState(currentState)
    }

    override fun pause() {
        currentState = stopwatchStateCalculator.calculatePausedState(currentState)
    }

    override fun stop() {
        currentState = StopwatchState.Paused(0)
    }

    override fun getStringTimeRepresentation(): String {
        val elapsedTime = when (val currentState = currentState) {
            is StopwatchState.Paused -> currentState.elapsedTime
            is StopwatchState.Running -> elapsedTimeCalculator.calculate(currentState)
        }
        return timestampMillisecondsFormatter.format(elapsedTime)
    }
}

