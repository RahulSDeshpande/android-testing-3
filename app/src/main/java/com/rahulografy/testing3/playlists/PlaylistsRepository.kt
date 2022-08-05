package com.rahulografy.testing3.playlists

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PlaylistsRepository
@Inject constructor(private val playlistsService: PlaylistsService) {

    suspend fun getPlaylists(): Flow<Result<List<PlaylistItem>>> {
        return playlistsService.getPlaylists()
    }
}
