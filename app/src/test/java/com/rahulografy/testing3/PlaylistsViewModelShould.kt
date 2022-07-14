package com.rahulografy.testing3

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.rahulografy.testing3.playlists.PlaylistsRepository
import com.rahulografy.testing3.playlists.PlaylistsViewModel
import com.rahulografy.testing3.utils.MainCoroutineScopeRule
import com.rahulografy.testing3.utils.getValueForTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class PlaylistsViewModelShould {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel = PlaylistsViewModel()
    private val repository: PlaylistsRepository = mock()

    @Test
    fun getPlaylistsFromRepository() {
        viewModel.getPlaylists(repository)

        viewModel.playlists.getValueForTest()

        verify(repository, times(1)).getPlaylists()
    }
}
