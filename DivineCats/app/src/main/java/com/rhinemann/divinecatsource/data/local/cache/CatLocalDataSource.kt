package com.rhinemann.divinecatsource.data.local.cache

import com.rhinemann.divinecatsource.data.local.cache.room.CatDao
import com.rhinemann.divinecatsource.data.local.cache.room.model.RoomCat

/**
 * Created by dronpascal on 09.11.2021.
 */
interface ICatLocalDataSource {

    suspend fun getAll(): List<RoomCat>
    suspend fun getById(id: String): RoomCat?
    suspend fun insertAll(cats: List<RoomCat>)
    suspend fun insert(cat: RoomCat)
    suspend fun delete(id: String)
}

class CatLocalDataSource(
    private val catDao: CatDao
) : ICatLocalDataSource {

    override suspend fun getAll(): List<RoomCat> = catDao.selectAll()

    override suspend fun getById(id: String): RoomCat? = catDao.select(id)

    override suspend fun insertAll(cats: List<RoomCat>) {
        catDao.insertAll(cats)
    }

    override suspend fun insert(cat: RoomCat) {
        catDao.insert(cat)
    }

    override suspend fun delete(id: String) {
        catDao.delete(id)
    }
}