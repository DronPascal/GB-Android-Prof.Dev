package com.rhinemann.divinecatsource.domain.interactor.random

import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.core.wrappers.UIComponent
import com.rhinemann.divinecatsource.data.ICatRepository
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by dronpascal on 28.10.2021.
 */
class GetRandomCat(
    private val catRepository: ICatRepository,
) : IGetRandomCat {
    override fun execute(): Flow<DataState<Cat>> = flow {
        try {
            emit(DataState.Loading(loadingState = LoadingState.Loading))
            val cat: Cat = catRepository.getRandomCat()
            emit(DataState.Loading(loadingState = LoadingState.Idle))
            emit(DataState.Data(cat))
        } catch (e: Exception) {
            emit(
                @Suppress("RemoveExplicitTypeArguments")  // Error without type T = <Cat>
                DataState.Response<Cat>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        message = e.message ?: "Unknown Error"
                    )
                )
            )
        } finally {
        }
    }.flowOn(Dispatchers.IO)
}