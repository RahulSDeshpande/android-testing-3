package com.rahulografy.testing3.playlists

import kotlinx.coroutines.flow.Flow

class PlaylistsRepository(private val playlistsService: PlaylistsService) {

    suspend fun getPlaylists(): Flow<Result<List<PlaylistItem>>> {
        return playlistsService.getPlaylists()
    }
}
