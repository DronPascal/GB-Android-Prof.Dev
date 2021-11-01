package com.gb.stopwatch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gb.stopwatch.data.local.timer.IStopwatchRepository
import com.gb.stopwatch.domain.entity.TimestampMillisecondsFormatter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by dronpascal on 01.11.2021.
 */
class StopwatchListViewModel(
    private val stopwatchRepository: IStopwatchRepository,
) : ViewModel() {

    private var job: Job? = null
    private val mutableTicker = MutableStateFlow(TimestampMillisecondsFormatter.DEFAULT_TIME)
    val ticker: StateFlow<String> = mutableTicker

    fun start() {
        if (job == null) startJob()
        stopwatchRepository.start()
    }

    private fun startJob() {
        viewModelScope.launch {
            while (isActive) {
                mutableTicker.value = stopwatchRepository.getStringTimeRepresentation()
                delay(20)
            }
        }
    }

    fun pause() {
        stopwatchRepository.pause()
        stopJob()
    }

    fun stop() {
        stopwatchRepository.stop()
        stopJob()
        clearValue()
    }

    private fun stopJob() {
        viewModelScope.coroutineContext.cancelChildren()
        job = null
    }

    private fun clearValue() {
        mutableTicker.value = TimestampMillisecondsFormatter.DEFAULT_TIME
    }

}

