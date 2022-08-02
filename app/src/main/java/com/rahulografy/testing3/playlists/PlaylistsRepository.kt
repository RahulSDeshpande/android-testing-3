package com.rahulografy.testing3.playlists

import kotlinx.coroutines.flow.flow

class PlaylistsRepository(private val playlistsService: PlaylistsService) {

    suspend fun getPlaylists() =
        flow<Result<List<PlaylistItem>>> { playlistsService.getPlaylists() }
}
