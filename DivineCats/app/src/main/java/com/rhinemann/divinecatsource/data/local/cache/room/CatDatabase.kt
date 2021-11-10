package com.rhinemann.divinecatsource.data.local.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rhinemann.divinecatsource.data.local.cache.room.model.RoomCat

/**
 * Created by dronpascal on 09.11.2021.
 */
@Database(
    entities = [RoomCat::class],
    version = 1,
    exportSchema = false
)
abstract class CatDatabase : RoomDatabase() {

    abstract val catDao: CatDao

    companion object {
        fun create(context: Context): CatDatabase =
            Room.databaseBuilder(
                context,
                CatDatabase::class.java,
                "cat_database"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}