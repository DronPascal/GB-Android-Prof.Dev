package com.rhinemann.divinecatsource.data.paging.cat

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.CatDomainMapper.toModel
import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.TheCatApi
import com.rhinemann.divinecatsource.domain.model.FilterOrder
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import com.rhinemann.divinecatsource.domain.model.cat.Category
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by dronpascal on 15.11.2021.
 */
class CatPagingSource(
    private val service: TheCatApi,
    private val breed: String?,
    private val categories: List<Category>,
    private val order: FilterOrder
) : PagingSource<Int, Cat>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {

        // Retrofit calls that return the body type throw either IOException for network
        // failures, or HttpException for any non-2xx HTTP status codes. This code reports all
        // errors to the UI, but you can inspect/wrap the exceptions to provide more context.
        return try {
            // Key may be null during a refresh, if no explicit key is passed into Pager
            // construction. Use 0 as default, because our API is indexed started at index 0
            val pageNumber = params.key ?: 0

            val response = service.getSearchPage(
                limit = params.loadSize,
                page = pageNumber,
                breed = breed,
                categories = categories.firstOrNull()?.name,
                order = order.apiValue
            )

            // Since 0 is the lowest page number, return null to signify no more pages should
            // be loaded before it.
            val prevKey = if (pageNumber > 0) pageNumber - 1 else null

            // This API defines that it's out of data when a page returns empty. When out of
            // data, we return `null` to signify no more pages should be loaded
            val nextKey = if (response.isNotEmpty()) pageNumber + 1 else null
            LoadResult.Page(
                data = response.map { it.toModel() },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}