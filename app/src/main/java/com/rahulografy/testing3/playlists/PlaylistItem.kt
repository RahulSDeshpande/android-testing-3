package com.rahulografy.testing3.playlists

import androidx.annotation.DrawableRes

data class PlaylistItem(
    val id: String,
    val name: String,
    val category: String,
    @DrawableRes val imageResId: Int
)
