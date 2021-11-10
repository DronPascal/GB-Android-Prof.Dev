package com.rhinemann.divinecatsource.presentation.screen.search.model

import com.rhinemann.divinecatsource.domain.model.cat.Breed

/**
 * Created by dronpascal on 10.11.2021.
 */
data class CheckableBreed(
    val id: String,
    val name: String,
    val imageUrl: String,
    var isChecked: Boolean = false
) {
    companion object {
        fun Breed.toCheckable() = CheckableBreed(
            id = this.id,
            name = this.name,
            imageUrl = this.imageUrl
        )
    }
}