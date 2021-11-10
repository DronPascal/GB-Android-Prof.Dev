package com.rhinemann.divinecatsource.data.remote

import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.TheCatApi
import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.model.BreedDto
import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.model.CatDto
import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.model.CategoryDto

/**
 * Created by dronpascal on 28.10.2021.
 */
interface ICatRemoteDataSource {
    suspend fun getRandomCat(): CatDto

    suspend fun getSearchPage(
        limit: Int,
        page: Int,
        breed: String?,
        categories: String?,
        order: String,
    ): List<CatDto>

    suspend fun getAllCategories(): List<CategoryDto>

    suspend fun getAllBreeds(): List<BreedDto>

}

class CatRemoteDataSource(
    private val theCatApi: TheCatApi
) : ICatRemoteDataSource {
    override suspend fun getRandomCat(): CatDto {
        return theCatApi.getRandomCat().first()
    }

    override suspend fun getSearchPage(
        limit: Int,
        page: Int,
        breed: String?,
        categories: String?,
        order: String
    ): List<CatDto> {
        return theCatApi.getSearchPage(limit, page, breed, categories, order)
    }

    override suspend fun getAllCategories(): List<CategoryDto> {
        return theCatApi.getAllCategories()
    }

    override suspend fun getAllBreeds(): List<BreedDto> {
        return theCatApi.getAllBreeds()
    }
}