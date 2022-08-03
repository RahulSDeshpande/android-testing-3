package com.rahulografy.testing3.playlists

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class PlaylistsViewModel : ViewModel() {

    private lateinit var playlistsRepository: PlaylistsRepository

    val playlists = MutableLiveData<Result<List<PlaylistItem>>>()

    var playlists2: LiveData<Result<List<PlaylistItem>>>? = null

    fun getPlaylists(playlistsRepository: PlaylistsRepository) {
        viewModelScope.launch {
            this@PlaylistsViewModel.playlistsRepository = playlistsRepository
            playlistsRepository
                .getPlaylists()
                .collect {
                    playlists.value = it
                    playlists2 = liveData {
                        emitSource(playlistsRepository.getPlaylists().asLiveData())
                    }
                }
        }
    }
}
