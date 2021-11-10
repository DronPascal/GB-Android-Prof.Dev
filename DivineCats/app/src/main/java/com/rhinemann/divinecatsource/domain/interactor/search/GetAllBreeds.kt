package com.rhinemann.divinecatsource.domain.interactor.search

import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.core.wrappers.UIComponent
import com.rhinemann.divinecatsource.data.ICatRepository
import com.rhinemann.divinecatsource.domain.model.cat.Breed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by dronpascal on 28.10.2021.
 */
class GetAllBreeds(
    private val catRepository: ICatRepository,
) : IGetAllBreeds {
    override fun execute(): Flow<DataState<List<Breed>>> = flow {
        try {
            emit(DataState.Loading(loadingState = LoadingState.Loading))
            val breeds: List<Breed> = catRepository.getAllBreeds()
            emit(DataState.Data(breeds))
        } catch (e: Exception) {
            emit(
                @Suppress("RemoveExplicitTypeArguments")  // Error without type T = <Cat>
                DataState.Response<List<Breed>>(
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