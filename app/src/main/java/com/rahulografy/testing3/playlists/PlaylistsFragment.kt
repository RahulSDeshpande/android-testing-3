package com.rahulografy.testing3.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahulografy.testing3.R
import com.rahulografy.testing3.databinding.FragmentPlaylistsBinding

class PlaylistsFragment : Fragment() {

    private lateinit var binding: FragmentPlaylistsBinding

    private val playlistsRepository = PlaylistsRepository()

    private val viewModel: PlaylistsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil
                .inflate(
                    inflater,
                    R.layout.fragment_playlists,
                    container,
                    false
                )

        viewModel.getPlaylists(playlistsRepository)
        viewModel.playlists.observe(viewLifecycleOwner) { result ->
            initList(result.getOrNull())
        }

        return binding.root
    }

    private fun initList(list: List<PlaylistItem>?) {
        if (list != null) {
            binding.rvPlaylists.layoutManager = LinearLayoutManager(context)
            binding.rvPlaylists.adapter = PlaylistsRecyclerViewAdapter(list = list)
        } else {
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = PlaylistsFragment()
    }
}
