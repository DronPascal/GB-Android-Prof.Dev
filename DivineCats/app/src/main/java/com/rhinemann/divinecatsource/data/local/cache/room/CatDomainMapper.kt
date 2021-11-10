package com.rhinemann.divinecatsource.data.local.cache.room

import com.rhinemann.divinecatsource.data.local.cache.room.model.RoomCat
import com.rhinemann.divinecatsource.domain.model.cat.Cat

/**
 * Created by dronpascal on 09.11.2021.
 */
object CatDomainMapper {

    internal fun RoomCat.toModel() = Cat(
        id = this.id,
        imageUrl = this.imageUrl,
        width = this.width,
        height = this.height,
    )

    internal fun Cat.toRoom() = RoomCat(
        id = this.id,
        imageUrl = this.imageUrl,
        width = this.width,
        height = this.height,
    )
}