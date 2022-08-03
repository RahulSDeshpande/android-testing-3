package com.rahulografy.testing3.playlists

import retrofit2.http.GET

interface PlaylistsApi {

    @GET("/getPlaylists")
    suspend fun getPlaylists(): List<PlaylistItem>
}
