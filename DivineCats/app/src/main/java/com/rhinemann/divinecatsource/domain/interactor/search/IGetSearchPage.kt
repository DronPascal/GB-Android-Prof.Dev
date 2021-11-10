package com.rhinemann.divinecatsource.domain.interactor.search

import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.domain.model.FilterOrder
import com.rhinemann.divinecatsource.domain.model.cat.Breed
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import com.rhinemann.divinecatsource.domain.model.cat.Category
import kotlinx.coroutines.flow.Flow

/**
 * Created by dronpascal on 06.11.2021.
 */
interface IGetSearchPage {
    fun execute(
        limit: Int,
        page: Int,
        breed: Breed? = null,
        categories: List<Category> = emptyList(),
        order: FilterOrder = FilterOrder.Descending
    ): Flow<DataState<List<Cat>>>
}
