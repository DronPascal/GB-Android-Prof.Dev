package com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api

import androidx.annotation.IntRange
import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.model.BreedDto
import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.model.CatDto
import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.model.CategoryDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by dronpascal on 28.10.2021.
 */
interface TheCatApi {

    @GET("images/search")
    suspend fun getRandomCat(): List<CatDto> // List.size is 1

    @GET("images/search")
    suspend fun getSearchPage(
        @Query("limit") @IntRange(from = 0, to = 100) limit: Int,
        @Query("page") page: Int,
        @Query("breed_id") breed: String? = null,
        @Query("category_ids") categories: String? = null,
        @Query("order") order: String = "DESC",
    ): List<CatDto>

    @GET("categories")
    suspend fun getAllCategories(): List<CategoryDto>

    @GET("breeds")
    suspend fun getAllBreeds(): List<BreedDto>
}

