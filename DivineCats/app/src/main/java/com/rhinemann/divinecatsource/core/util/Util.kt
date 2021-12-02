package com.rhinemann.divinecatsource.core.util

/**
 * Created by dronpascal on 25.11.2021.
 */
object Util {

    fun getScaledSize(sourceSize: Pair<Int, Int>, maxSize: Pair<Int, Int>): Pair<Int, Int> {
        val scaling = minOf(
            1f * maxSize.first / sourceSize.first,
            1f * maxSize.second / sourceSize.second
        )
        return (sourceSize.first * scaling).toInt() to (sourceSize.second * scaling).toInt()
    }

}