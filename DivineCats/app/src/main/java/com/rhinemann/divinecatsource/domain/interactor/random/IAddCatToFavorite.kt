package com.rhinemann.divinecatsource.domain.interactor.random

import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import kotlinx.coroutines.flow.Flow

/**
 * Created by dronpascal on 09.11.2021.
 */
interface IAddCatToFavorite {
    fun execute(cat: Cat): Flow<DataState<Unit>>
}