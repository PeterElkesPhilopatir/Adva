package com.peter.data.di

import com.peter.domain.repositories.PhotosRepository
import com.peter.domain.usecases.GetPhotosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HotelsUseCasesModule {
    @Singleton
    @Provides
    fun provideGetPhotosUseCase(photosRepository: PhotosRepository): GetPhotosUseCase =
        GetPhotosUseCase(photosRepository)

}
