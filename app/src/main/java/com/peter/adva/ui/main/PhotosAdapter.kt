package com.peter.adva.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peter.adva.R
import com.peter.adva.databinding.RowPhotoBinding
import com.peter.domain.models.Photo

class PhotosAdapter(private val onClickListener: OnPhotoClickListener)
    : ListAdapter<Photo, PhotoViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = DataBindingUtil.inflate<RowPhotoBinding>(
            LayoutInflater.from(parent.context), R.layout.row_photo, parent, false
        )

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }

        holder.itemView.setOnClickListener {
            getItem(position)?.let { it1 -> onClickListener.onClick(it1) }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }
    }


}

class PhotoViewHolder(private var binding:RowPhotoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(Item: Photo) {
        binding.data = Item
        binding.executePendingBindings()
    }
}

class OnPhotoClickListener(val clickListener: (item: Photo) -> Unit) {
    fun onClick(item: Photo) = clickListener(item)
}