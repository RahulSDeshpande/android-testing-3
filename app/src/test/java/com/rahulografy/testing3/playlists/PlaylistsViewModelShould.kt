package com.rahulografy.testing3.playlists

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rahulografy.testing3.utils.BaseUnitTest
import com.rahulografy.testing3.utils.getValueForTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PlaylistsViewModelShould : BaseUnitTest() {

    private val viewModel = PlaylistsViewModel()
    private val repository: PlaylistsRepository = mock()

    private val playlists = mock<List<PlaylistItem>>()
    private val successPlaylistsResult = Result.success(playlists)
    private val errorPlaylistsResult = RuntimeException("Something went wrong")

    init {
        runTest {
            whenever(methodCall = repository.getPlaylists())
                .thenReturn(flow { emit(successPlaylistsResult) })
        }
    }

    @Test
    fun getPlaylistsFromRepository() {
        viewModel.getPlaylists(repository)

        viewModel.playlists.getValueForTest()

        runTest {
            verify(
                mock = repository,
                mode = times(1)
            ).getPlaylists()
        }
    }

    @Test
    fun emitsPlaylistsFromRepository() {
        viewModel.getPlaylists(repository)

        assertEquals(
            successPlaylistsResult,
            viewModel.playlists.getValueForTest()
        )
    }

    @Test
    fun emitErrorWhenReceiveError() {
        // viewModel.getPlaylists(repository)

        runTest {
            whenever(methodCall = repository.getPlaylists())
                .thenReturn(flow { emit(Result.failure(errorPlaylistsResult)) })
        }

        val viewModel = PlaylistsViewModel()
        viewModel.getPlaylists(repository)
        assertEquals(
            errorPlaylistsResult,
            viewModel.playlists2.getValueForTest()?.exceptionOrNull()
        )
    }
}
