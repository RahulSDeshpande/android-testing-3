package com.rahulografy.testing3.playlists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaylistsViewModel : ViewModel() {

    val playlists = MutableLiveData<Result<List<PlaylistItem>>>()

    private lateinit var playlistsRepository: PlaylistsRepository

    fun getPlaylists(playlistsRepository: PlaylistsRepository) {
        this.playlistsRepository = playlistsRepository
        playlistsRepository.getPlaylists()
    }
}
