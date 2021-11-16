package com.rhinemann.divinecatsource.domain.interactor.search_result

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rhinemann.divinecatsource.data.paging.cat.CatPagingSource
import com.rhinemann.divinecatsource.data.remote.retrofit.the_cat_api.TheCatApi
import com.rhinemann.divinecatsource.domain.model.FilterOrder
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import com.rhinemann.divinecatsource.domain.model.cat.Category
import kotlinx.coroutines.flow.Flow

/**
 * Created by dronpascal on 16.11.2021.
 */
class GetSearchPageStream(
    private val service: TheCatApi
) : IGetSearchPageStream {

    override fun execute(
        breed: String?,
        categories: List<Category>,
        order: FilterOrder
    ): Flow<PagingData<Cat>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CatPagingSource(
                    service = service,
                    breed = breed,
                    categories = categories,
                    order = order
                )
            }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 9
    }
}
