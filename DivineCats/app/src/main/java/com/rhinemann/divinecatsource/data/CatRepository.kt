package com.rhinemann.divinecatsource.data

import com.rhinemann.divinecatsource.data.local.cache.ICatLocalDataSource
import com.rhinemann.divinecatsource.data.remote.ICatRemoteDataSource
import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.CatDomainMapper.toModel
import com.rhinemann.divinecatsource.domain.model.FilterOrder
import com.rhinemann.divinecatsource.domain.model.cat.Breed
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import com.rhinemann.divinecatsource.domain.model.cat.Category

/**
 * Created by dronpascal on 28.10.2021.
 */
interface ICatRepository {
    suspend fun getRandomCat(): Cat

    suspend fun getSearchPage(
        limit: Int,
        page: Int,
        breed: Breed?,
        categories: List<Category>,
        order: FilterOrder,
    ): List<Cat>

    suspend fun getAllCategories(): List<Category>

    suspend fun getAllBreeds(): List<Breed>

//    suspend fun getFavoriteCats(): List<Cat>
//
//    suspend fun addToFavoriteCats(cat: Cat)
}

class CatRepository(
    private val catRemoteDataSource: ICatRemoteDataSource,
    private val catLocalDataSource: ICatLocalDataSource
) : ICatRepository {
    override suspend fun getRandomCat(): Cat {
        return catRemoteDataSource.getRandomCat().toModel()
    }

    override suspend fun getSearchPage(
        limit: Int,
        page: Int,
        breed: Breed?,
        categories: List<Category>,
        order: FilterOrder
    ): List<Cat> {
        return catRemoteDataSource.getSearchPage(
            limit = limit,
            page = page,
            breed = breed?.id,
            categories = categories.joinToString(separator = ",") { it.id.toString() },
            order = order.apiValue
        ).map { it.toModel() }
    }

    override suspend fun getAllCategories(): List<Category> {
        return catRemoteDataSource.getAllCategories().map { it.toModel() }
    }

    override suspend fun getAllBreeds(): List<Breed> {
        return catRemoteDataSource.getAllBreeds().map { it.toModel() }
    }

//    override suspend fun getFavoriteCats(): List<Cat> {
//        return catLocalDataSource.getAll().map { it.toModel() }
//    }
//
//    override suspend fun addToFavoriteCats(cat: Cat) {
//        catLocalDataSource.insert(cat.toRoom())
//    }
}