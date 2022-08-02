package com.rahulografy.testing3.playlists

class PlaylistsRepository(
    private val playlistsApiService: PlaylistsApiService
) {

    suspend fun getPlaylists() = playlistsApiService.getPlaylists()
}
