package com.rahulografy.testing3.playlists

import kotlinx.coroutines.flow.Flow

class PlaylistsRepository {

    suspend fun getPlaylists(): Flow<Result<List<PlaylistItem>>> {
        TODO("Not yet implemented")
    }
}
