package com.peter.data.network

import com.peter.data.network.responses.photos_response.PhotosResponse
import com.peter.data.network.responses.photos_response.PhotosResponseItem
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PhotosAPI {
    @GET("photos")
    fun getPhotosAsync(): Deferred<PhotosResponse>
}