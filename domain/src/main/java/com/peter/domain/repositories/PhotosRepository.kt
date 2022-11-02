package com.peter.domain.repositories

import com.peter.domain.base.DataState
import com.peter.domain.models.Photo
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {
    fun getRepository() : Flow<DataState<List<Photo>>>
}