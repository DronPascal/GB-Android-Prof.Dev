package com.rhinemann.divinecatsource.domain.interactor.search

import com.rhinemann.divinecatsource.core.wrappers.DataState
import com.rhinemann.divinecatsource.domain.model.cat.Breed
import kotlinx.coroutines.flow.Flow

/**
 * Created by dronpascal on 06.11.2021.
 */
interface IGetAllBreeds {
    fun execute(): Flow<DataState<List<Breed>>>
}
