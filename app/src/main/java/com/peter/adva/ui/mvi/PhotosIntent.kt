package com.peter.adva.ui.mvi

import com.peter.domain.models.Photo

sealed class PhotosIntent {
    data class GetPhotos(val page: Int) : PhotosIntent()
    data class ChoosePhoto(val photo: Photo) : PhotosIntent()
}