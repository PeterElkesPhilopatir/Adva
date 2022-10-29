package com.peter.domain.usecases

import com.peter.domain.repositories.PhotosRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val photosRepository: PhotosRepository) {
    operator fun invoke(page : Int) = photosRepository.getRepository(page)
}