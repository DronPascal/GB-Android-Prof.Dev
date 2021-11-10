package com.rhinemann.divinecatsource.domain.interactor.search

import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.core.wrappers.UIComponent
import com.rhinemann.divinecatsource.data.ICatRepository
import com.rhinemann.divinecatsource.domain.model.FilterOrder
import com.rhinemann.divinecatsource.domain.model.cat.Breed
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import com.rhinemann.divinecatsource.domain.model.cat.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by dronpascal on 28.10.2021.
 */
class GetSearchPage(
    private val catRepository: ICatRepository,
) : IGetSearchPage {
    override fun execute(
        limit: Int,
        page: Int,
        breed: Breed?,
        categories: List<Category>,
        order: FilterOrder
    ): Flow<DataState<List<Cat>>> = flow {
        try {
            emit(DataState.Loading(loadingState = LoadingState.Loading))
            val cats: List<Cat> = catRepository.getSearchPage(limit, page, breed, categories, order)
            emit(DataState.Data(cats))
        } catch (e: Exception) {
            emit(
                @Suppress("RemoveExplicitTypeArguments")  // Error without type T = <Cat>
                DataState.Response<List<Cat>>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        message = e.message ?: "Unknown Error"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(loadingState = LoadingState.Idle))
        }
    }.flowOn(Dispatchers.IO)
}