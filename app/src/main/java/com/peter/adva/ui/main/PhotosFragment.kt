package com.peter.adva.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.peter.adva.databinding.FragmentPhotosBinding
import com.peter.adva.ui.mvi.PhotosIntent
import com.peter.adva.ui.mvi.PhotosViewModel
import com.peter.domain.base.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosFragment : Fragment() {
    private lateinit var binding: FragmentPhotosBinding
    private val viewModel: PhotosViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPhotosBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.intentChannel.send(PhotosIntent.GetPhotos(0))
        }

        observeViewModel()


    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.photos.collect { dataState ->
                when (dataState.status) {
                    DataState.Status.INACTIVE -> {
                        // Do nothing
                    }
                    DataState.Status.SUCCESS -> {
                        dataState.data.let { list ->
                            binding.rvRecycler.visibility = View.VISIBLE
                            binding.lavError.visibility = View.GONE
                            binding.lavLoading.visibility = View.GONE

                            val adapter = PhotosAdapter(onClickListener = OnPhotoClickListener {
                                lifecycleScope.launch {
                                    viewModel.intentChannel.send(PhotosIntent.ChoosePhoto(it))
                                }
                                findNavController().navigate(
                                    PhotosFragmentDirections.actionPhotosFragmentToImageFragment()
                                )
                            })
                            binding.rvRecycler.adapter = adapter
                            adapter.submitList(list)
                        }
                    }
                    DataState.Status.ERROR -> {
                        onError(dataState.error)
                    }
                    DataState.Status.LOADING -> {
                        onLoading()
                    }
                }
            }
        }
    }

    private fun onError(error: DataState.HttpError?) {

        binding.rvRecycler.visibility = View.GONE
        binding.lavError.visibility = View.VISIBLE
        binding.lavLoading.visibility = View.GONE
        Toast.makeText(
            context,
            "${error!!.throwableMessage} ${error.serverMessage} ${error.errorCode}",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onLoading() {
        binding.rvRecycler.visibility = View.GONE
        binding.lavError.visibility = View.GONE
        binding.lavLoading.visibility = View.VISIBLE
    }


}