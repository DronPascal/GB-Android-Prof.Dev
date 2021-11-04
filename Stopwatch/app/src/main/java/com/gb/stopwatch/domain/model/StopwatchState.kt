package com.gb.stopwatch.domain.model

/**
 * Created by dronpascal on 01.11.2021.
 */
sealed class StopwatchState {

    data class Paused(
        val elapsedTime: Long
    ) : StopwatchState()

    data class Running(
        val startTime: Long,
        val elapsedTime: Long
    ) : StopwatchState()
}