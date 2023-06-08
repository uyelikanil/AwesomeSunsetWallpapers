package com.anilyilmaz.awesomesunsetwallpapers.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.anilyilmaz.awesomesunsetwallpapers.R
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.databinding.WallpaperListCardBinding

class WallpapersAdapter(diffCallback: DiffUtil.ItemCallback<Photo>):
    PagingDataAdapter<Photo, WallpaperViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder =
        WallpaperViewHolder(WallpaperListCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val item = getItem(position)

        if(item != null) {
            holder.bind(item)
        }
    }
}

class WallpaperViewHolder(private val binding: WallpaperListCardBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(photo: Photo){
        binding.imageView.load(photo.src.portrait) {
            crossfade(true)
            placeholder(R.mipmap.ic_launcher_foreground)
        }

        binding.imageView.setOnClickListener {
            val action = HomeFragmentDirections.startWallpaperDetailFragment(photo.id)
            it.findNavController().navigate(action)
        }
    }
}

object WallpapersComparator : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}