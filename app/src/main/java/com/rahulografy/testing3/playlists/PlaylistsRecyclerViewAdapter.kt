package com.rahulografy.testing3.playlists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        val playlistItem = list[position]
        holder.binding.tvPlaylistName.text = playlistItem.name
        holder.binding.tvPlaylistCategoryName.text = playlistItem.category
        holder.binding.ivPlaylistImage.setImageResource(playlistItem.imageResId)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: PlaylistItemBinding) : RecyclerView.ViewHolder(binding.root)
}
