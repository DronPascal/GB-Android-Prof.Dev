package com.rhinemann.divinecatsource.domain.interactor.random

import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import kotlinx.coroutines.flow.Flow

/**
 * Created by dronpascal on 29.10.2021.
 */
interface IGetRandomCat {
    fun execute(): Flow<DataState<Cat>>
}