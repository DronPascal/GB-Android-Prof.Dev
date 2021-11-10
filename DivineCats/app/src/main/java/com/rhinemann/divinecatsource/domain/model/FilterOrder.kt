package com.rhinemann.divinecatsource.domain.model

/**
 * Created by dronpascal on 06.11.2021.
 */
sealed class FilterOrder(val apiValue: String) {
    object Ascending : FilterOrder("ASC")
    object Descending : FilterOrder("DESC")
}