package com.rhinemann.divinecatsource.data.local.cache.room

import androidx.room.*
import com.rhinemann.divinecatsource.data.local.cache.room.model.RoomCat

/**
 * Created by dronpascal on 09.11.2021.
 */
@Dao
interface CatDao {

    @Query("SELECT * FROM ${RoomCat.TABLE_NAME} ORDER BY ${RoomCat.CAT_ID} ASC")
    suspend fun selectAll(): List<RoomCat>

    @Query("SELECT * FROM ${RoomCat.TABLE_NAME} WHERE ${RoomCat.CAT_ID} = :catId")
    suspend fun select(catId: String): RoomCat

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(cats: List<RoomCat>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cat: RoomCat)

    @Query("DELETE FROM ${RoomCat.TABLE_NAME} WHERE ${RoomCat.CAT_ID} = :catId")
    suspend fun delete(catId: String)
}