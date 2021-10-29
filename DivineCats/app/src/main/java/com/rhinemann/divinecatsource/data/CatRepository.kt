package com.rhinemann.divinecatsource.data

import com.rhinemann.divinecatsource.data.remote.ICatRemoteDataSource
import com.rhinemann.divinecatsource.data.remote.retrofit.cat.CatDomainMapper.toModel
import com.rhinemann.divinecatsource.domain.model.Cat

/**
 * Created by dronpascal on 28.10.2021.
 */
interface ICatRepository {
    suspend fun getCat(): Cat
}

class CatRepository(
    private val catRemoteDataSource: ICatRemoteDataSource
) : ICatRepository {
    override suspend fun getCat(): Cat {
        return catRemoteDataSource.getCat().toModel()
    }
}