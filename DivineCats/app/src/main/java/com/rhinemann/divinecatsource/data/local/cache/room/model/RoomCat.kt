package com.rhinemann.divinecatsource.data.local.cache.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by dronpascal on 09.11.2021.
 */
@Entity(tableName = RoomCat.TABLE_NAME)
class RoomCat(

    @ColumnInfo(name = CAT_ID)
    @PrimaryKey val id: String,

    @ColumnInfo(name = IMAGE_URL)
    val imageUrl: String,

    @ColumnInfo(name = WIDTH)
    val width: Int,

    @ColumnInfo(name = HEIGHT)
    val height: Int,
) {
    companion object {

        const val TABLE_NAME = "cats"

        const val CAT_ID = "id"
        const val IMAGE_URL = "image_url"
        const val WIDTH = "width"
        const val HEIGHT = "height"
    }
}