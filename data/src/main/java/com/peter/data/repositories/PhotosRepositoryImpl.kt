package com.peter.data.repositories

import com.peter.data.network.PhotosAPI
import com.peter.domain.base.DataState
import com.peter.domain.models.Photo
import com.peter.domain.repositories.PhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(private val api: PhotosAPI) : PhotosRepository {
    override fun getRepository(): Flow<DataState<List<Photo>>> =
        flow {
            emit(DataState.loading())
            val response = api.getPhotosAsync().await()
            try {
                val list = mutableListOf<Photo>()
                response.forEach {
                    list.add(
                        Photo(
                            id = it.id,
                            title = it.title,
//                            url = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Facebook_icon.svg/2048px-Facebook_icon.svg.png"
                            url = it.thumbnailUrl
                        )
                    )
                }
                emit(DataState.success(list))
            } catch (e: Exception) {
                emit(
                    DataState.error(
                        DataState.HttpError(
                            e.message,
                            "Error occurred",
                            0
                        )
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
}