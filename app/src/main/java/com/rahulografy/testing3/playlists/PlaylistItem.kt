package com.rahulografy.testing3.playlists

import androidx.annotation.DrawableRes
import com.rahulografy.testing3.R

data class PlaylistItem(
    val id: String,
    val name: String,
    val category: String,
    @DrawableRes val imageResId: Int = R.drawable.playlist
)
