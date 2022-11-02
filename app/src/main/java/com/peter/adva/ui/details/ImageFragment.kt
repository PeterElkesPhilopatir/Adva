package com.peter.adva.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ablanco.zoomy.Zoomy
import com.peter.adva.MainActivity
import com.peter.adva.R
import com.peter.adva.databinding.FragmentImageBinding
import com.peter.adva.ui.main.OnPhotoClickListener
import com.peter.adva.ui.main.PhotosAdapter
import com.peter.adva.ui.mvi.PhotosIntent
import com.peter.adva.ui.mvi.PhotosViewModel
import com.peter.domain.base.DataState
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class ImageFragment : Fragment() {
    private lateinit var binding: FragmentImageBinding
    private val viewModel: PhotosViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.selectedPhoto.collect { photo ->

                Picasso.get().load(photo.url)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.mipmap.ic_launcher_round)
                    .into(binding.image)

                val builder: Zoomy.Builder =
                    Zoomy.Builder(context as MainActivity).target(binding.image)
                builder.enableImmersiveMode(true)
                    .animateZooming(true)
                    .interpolator(OvershootInterpolator())
                builder.register()
            }
        }
    }


}