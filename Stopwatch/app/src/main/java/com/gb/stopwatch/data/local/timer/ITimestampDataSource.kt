package com.gb.stopwatch.data.local.timer

/**
 * Created by dronpascal on 01.11.2021.
 */
interface ITimestampDataSource {
    fun getMilliseconds(): Long
}