package com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api

import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.model.BreedDto
import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.model.CatDto
import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.model.CategoryDto
import com.rhinemann.divinecatsource.domain.model.cat.Breed
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import com.rhinemann.divinecatsource.domain.model.cat.Category

/**
 * Created by dronpascal on 28.10.2021.
 */
object CatDomainMapper {

    internal fun CatDto.toModel() = Cat(
        id = this.id,
        imageUrl = this.url,
        width = this.width,
        height = this.height,
    )

    internal fun CategoryDto.toModel() = Category(
        id = id,
        name = name,
    )

    internal fun BreedDto.toModel() =
        Breed(
            id = id,
            name = name,
            imageUrl = image?.url ?: "",
        )

}