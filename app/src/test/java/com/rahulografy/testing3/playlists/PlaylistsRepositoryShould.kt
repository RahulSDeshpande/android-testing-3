package com.rahulografy.testing3.playlists

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rahulografy.testing3.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PlaylistsRepositoryShould : BaseUnitTest() {

    private val playlistsApiService: PlaylistsApiService = mock()

    private val playlists = mock<List<PlaylistItem>>()

    private val exception = RuntimeException("Something went wrong")

    private suspend fun mockSuccessfulCase(): PlaylistsRepository {
        whenever(playlistsApiService.getPlaylists())
            .thenReturn(flow { emit(Result.success(playlists)) })

        return PlaylistsRepository(playlistsApiService)
    }

    @Test
    fun getPlaylistsFromApiService() =
        runTest {
            val repository = PlaylistsRepository(playlistsApiService)
            repository.getPlaylists()

            verify(playlistsApiService, times(1)).getPlaylists()
        }

    @Test
    fun emitPlaylistsFromApiService() =
        runTest {
            val repository = mockSuccessfulCase()

            assertEquals(
                playlists,
                repository.getPlaylists().first().getOrNull()
            )
        }

    @Test
    fun propagateErrors() = runTest {
        val repository = mockErrorCase()

        assertEquals(
            exception,
            repository.getPlaylists().first().exceptionOrNull()
        )
    }

    private suspend fun mockErrorCase(): PlaylistsRepository {
        whenever(playlistsApiService.getPlaylists()).thenReturn(
            flow { emit(Result.failure(exception)) }
        )

        val repository = PlaylistsRepository(playlistsApiService)
        return repository
    }
}
