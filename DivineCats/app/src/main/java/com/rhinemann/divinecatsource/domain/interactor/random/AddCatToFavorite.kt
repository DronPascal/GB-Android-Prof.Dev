package com.rhinemann.divinecatsource.domain.interactor.random

import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.core.wrappers.UIComponent
import com.rhinemann.divinecatsource.data.local.cache.ICatLocalDataSource
import com.rhinemann.divinecatsource.data.local.cache.room.CatDomainMapper.toRoom
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by dronpascal on 09.11.2021.
 */
class AddCatToFavorite(
    private val catLocalDataSource: ICatLocalDataSource,
) : IAddCatToFavorite {
    override fun execute(cat: Cat): Flow<DataState<Unit>> = flow {
        try {
            emit(DataState.Loading(loadingState = LoadingState.Loading))
            catLocalDataSource.insert(cat.toRoom())
            emit(DataState.Data())
        } catch (e: Exception) {
            emit(
                @Suppress("RemoveExplicitTypeArguments")  // Error without type T = <Cat>
                DataState.Response<Unit>(
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