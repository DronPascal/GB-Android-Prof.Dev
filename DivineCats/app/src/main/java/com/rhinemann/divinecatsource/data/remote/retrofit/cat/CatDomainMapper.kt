package com.rhinemann.divinecatsource.data.remote.retrofit.cat

import com.rhinemann.divinecatsource.domain.model.Cat

/**
 * Created by dronpascal on 28.10.2021.
 */
object CatDomainMapper {

    internal fun CatDto.toModel(): Cat = Cat(
        id = this.id,
        url = this.url
    )

}