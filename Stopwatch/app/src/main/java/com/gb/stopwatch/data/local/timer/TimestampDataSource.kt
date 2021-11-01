package com.gb.stopwatch.data.local.timer

/**
 * Created by dronpascal on 01.11.2021.
 */
class TimestampDataSource : ITimestampDataSource {
    override fun getMilliseconds(): Long {
        return System.currentTimeMillis()
    }
}
