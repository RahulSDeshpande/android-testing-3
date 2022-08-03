package com.rahulografy.testing3.playlists

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlaylistsService(private val playlistsApi: PlaylistsApi) {

    suspend fun getPlaylists(): Flow<Result<List<PlaylistItem>>> {
        return flow {
            emit(Result.success(playlistsApi.getPlaylists()))
        }.catch {
            emit(Result.failure(RuntimeException("Network Error!!!!!")))
        }
    }
}
