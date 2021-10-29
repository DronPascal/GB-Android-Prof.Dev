package com.rhinemann.divinecatsource.domain.interactor

import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.domain.model.Cat
import kotlinx.coroutines.flow.Flow

/**
 * Created by dronpascal on 29.10.2021.
 */
interface IGetCat {
    fun execute(): Flow<DataState<Cat>>
}