package com.rahulografy.testing3.playlists

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rahulografy.testing3.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PlaylistsServiceShould : BaseUnitTest() {

    private lateinit var playlistsService: PlaylistsService

    private val playlistsApi: PlaylistsApi = mock()

    private val playlists: List<PlaylistItem> = mock()

    @Test
    fun getPlaylistsFromApiService() =
        runTest {
            playlistsService = PlaylistsService(playlistsApi)
            playlistsService.getPlaylists().first()
            verify(playlistsApi, times(1)).getPlaylists()
        }

    @Test
    fun convertValuesToFlowResultAndEmitThem() =
        runTest {
            mockSuccessfulCase()

            assertEquals(
                Result.success(playlists),
                playlistsService.getPlaylists().first()
            )
        }

    @Test
    fun emitsErrorResultWhenNetworkFails() =
        runTest {
            mockErrorCase()

            assertEquals(
                "Network Error!!!!!",
                playlistsService.getPlaylists().first().exceptionOrNull()?.message
            )
        }

    private fun mockErrorCase() {
        whenever(playlistsApi.getPlaylists())
            .thenThrow(RuntimeException("Network Error!!"))

        playlistsService = PlaylistsService(playlistsApi)
    }

    private fun mockSuccessfulCase() {
        whenever(playlistsApi.getPlaylists()).thenReturn(playlists)

        playlistsService = PlaylistsService(playlistsApi)
    }
}
