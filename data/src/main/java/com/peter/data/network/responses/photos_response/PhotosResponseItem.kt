package com.peter.data.network.responses.photos_response

data class PhotosResponseItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)