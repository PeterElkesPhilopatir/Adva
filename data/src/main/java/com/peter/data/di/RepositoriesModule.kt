package com.peter.data.di

import com.peter.data.network.PhotosAPI
import com.peter.data.repositories.PhotosRepositoryImpl
import com.peter.domain.repositories.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {
    @Singleton
    @Provides
    fun providePhotosRepository(photosAPI: PhotosAPI): PhotosRepository =
        PhotosRepositoryImpl(photosAPI)

}