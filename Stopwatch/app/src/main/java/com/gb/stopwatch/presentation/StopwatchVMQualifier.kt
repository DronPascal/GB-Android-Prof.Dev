package com.gb.stopwatch.presentation

import org.koin.core.qualifier.Qualifier

/**
 * Created by dronpascal on 02.11.2021.
 */
data class StopwatchVMQualifier(val key: String) : Qualifier {

    override val value = "QualifierName"

    override fun toString() = key
}