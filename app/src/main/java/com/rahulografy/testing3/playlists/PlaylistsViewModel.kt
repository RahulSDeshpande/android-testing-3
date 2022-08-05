package com.rahulografy.testing3.playlists

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PlaylistsViewModel
@Inject constructor(private val playlistsRepository: PlaylistsRepository) : ViewModel() {

    val playlists = MutableLiveData<Result<List<PlaylistItem>>>()

    var playlists2: LiveData<Result<List<PlaylistItem>>>? = null

    fun getPlaylists() {
        viewModelScope.launch {
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
