package com.rhinemann.divinecatsource.domain.interactor.search_result

import androidx.paging.PagingData
import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.domain.model.FilterOrder
import com.rhinemann.divinecatsource.domain.model.cat.Breed
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import com.rhinemann.divinecatsource.domain.model.cat.Category
import kotlinx.coroutines.flow.Flow

/**
 * Created by dronpascal on 16.11.2021.
 */
interface IGetSearchPageStream {

    fun execute(
        breed: String? = null,
        categories: List<Category> = emptyList(),
        order: FilterOrder = FilterOrder.Descending
    ): Flow<PagingData<Cat>>
}
