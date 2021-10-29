package com.rhinemann.divinecatsource.data.remote

import com.rhinemann.divinecatsource.data.remote.retrofit.cat.CatApi
import com.rhinemann.divinecatsource.data.remote.retrofit.cat.CatDto

/**
 * Created by dronpascal on 28.10.2021.
 */
interface ICatRemoteDataSource {
    suspend fun getCat(): CatDto
}

class CatRemoteDataSource(
    private val catApi: CatApi
) : ICatRemoteDataSource {
    override suspend fun getCat(): CatDto {
        return catApi.getCat().first()
    }
}