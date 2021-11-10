package com.rhinemann.divinecatsource.presentation.screen.search.model

import com.rhinemann.divinecatsource.domain.model.cat.Category

/**
 * Created by dronpascal on 10.11.2021.
 */
data class CheckableCategory(
    val id: Int,
    val name: String,
    var isChecked: Boolean = false
) {
    companion object {
        fun Category.toCheckable() = CheckableCategory(
            id = this.id,
            name = this.name,
        )
    }
}