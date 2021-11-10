package com.rhinemann.divinecatsource.domain.interactor.search

import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.core.wrappers.UIComponent
import com.rhinemann.divinecatsource.data.ICatRepository
import com.rhinemann.divinecatsource.domain.model.cat.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by dronpascal on 28.10.2021.
 */
class GetAllCategories(
    private val catRepository: ICatRepository,
) : IGetAllCategories {
    override fun execute(): Flow<DataState<List<Category>>> = flow {
        try {
            emit(DataState.Loading(loadingState = LoadingState.Loading))
            val categories: List<Category> = catRepository.getAllCategories()
            emit(DataState.Data(categories))
        } catch (e: Exception) {
            emit(
                @Suppress("RemoveExplicitTypeArguments")  // Error without type T = <Cat>
                DataState.Response<List<Category>>(
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