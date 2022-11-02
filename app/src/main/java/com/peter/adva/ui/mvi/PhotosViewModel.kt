package com.peter.adva.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peter.domain.base.DataState
import com.peter.domain.models.Photo
import com.peter.domain.usecases.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val getPhotosUseCase: GetPhotosUseCase) :
    ViewModel() {
    val intentChannel = Channel<PhotosIntent>(Channel.UNLIMITED)

    private val _photos: MutableStateFlow<DataState<List<Photo>>> =
        MutableStateFlow(DataState())
    val photos = _photos.asStateFlow()

    private val _selectedPhoto: MutableStateFlow<Photo> =
        MutableStateFlow(Photo())
    val selectedPhoto = _selectedPhoto.asStateFlow()

    init {
        processIntent()
    }

    private fun processIntent() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                when (it) {
                    is PhotosIntent.GetPhotos -> loadPhotos()
                    is PhotosIntent.ChoosePhoto -> selectPhoto(it.photo)
                }
            }
        }
    }

    private fun selectPhoto(photo: Photo) {
        _selectedPhoto.value = photo
    }

    private fun loadPhotos() {
        viewModelScope.launch {
            getPhotosUseCase.invoke().collectLatest { _photos.value = it }
        }
    }


}