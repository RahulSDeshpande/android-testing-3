package com.rahulografy.testing3.playlists

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlaylistsService
@Inject constructor(private val playlistsApi: PlaylistsApi) {

    suspend fun getPlaylists(): Flow<Result<List<PlaylistItem>>> {
        return flow {
            emit(Result.success(playlistsApi.getPlaylists()))
        }.catch {
            emit(Result.failure(RuntimeException("Network Error!!!!!")))
        }
    }
}
