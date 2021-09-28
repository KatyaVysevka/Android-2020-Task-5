package com.example.task5.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.task5.R
import com.example.task5.data.CatPhoto
import com.example.task5.databinding.ItemBinding

class CatAdapter:PagingDataAdapter <CatPhoto, CatAdapter.PhotoViewHolder> (PHOTO_COMPARATOR){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) holder.bind(currentItem)
    }

    class PhotoViewHolder(private val binding: ItemBinding):
        RecyclerView.ViewHolder (binding.root){

        fun bind (photo: CatPhoto){
            binding.apply {
                Glide.with(itemView)
                    .load(photo.url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_baseline_error_24)
                    .into(imageCat)

                textViewCat.text = photo.breeds[0].name ?: "Lovely Cat"
            }

        }

    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback <CatPhoto>(){
            override fun areItemsTheSame(oldItem: CatPhoto, newItem: CatPhoto): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CatPhoto,
                newItem: CatPhoto
            ): Boolean = oldItem == newItem

        }
    }
}


