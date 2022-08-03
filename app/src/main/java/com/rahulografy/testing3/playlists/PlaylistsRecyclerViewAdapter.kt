package com.rahulografy.testing3.playlists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahulografy.testing3.R
import com.rahulografy.testing3.databinding.PlaylistItemBinding

class PlaylistsRecyclerViewAdapter(
    private val list: List<PlaylistItem>
) : RecyclerView.Adapter<PlaylistsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val playlistItem = list[bindingAdapterPosition]
            binding.tvPlaylistName.text = playlistItem.name
            binding.tvPlaylistCategoryName.text = playlistItem.category
            binding.ivPlaylistImage.setImageResource(R.mipmap.playlist)
        }
    }
}
