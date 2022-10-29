package com.peter.data.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosAPI {
    @GET("photos")
    fun getPhotosAsync(
        @Query("per_page") per_page: Int = 10,
        @Query("page") page: Int,

    ): Deferred<String>
}