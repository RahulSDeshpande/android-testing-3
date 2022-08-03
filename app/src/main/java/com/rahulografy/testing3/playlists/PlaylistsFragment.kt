package com.rahulografy.testing3.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahulografy.testing3.R
import com.rahulografy.testing3.databinding.FragmentPlaylistsBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaylistsFragment : Fragment() {

    private lateinit var binding: FragmentPlaylistsBinding

    private val viewModel: PlaylistsViewModel by viewModels()

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://a641e3c6-796c-438e-a2b9-454ca439269d.mock.pstmn.io")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val playlistsApi = retrofit.create(PlaylistsApi::class.java)

    private val playlistsService = PlaylistsService(playlistsApi)

    private val playlistsRepository = PlaylistsRepository(playlistsService)

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
            Toast.makeText(activity, "List if empty", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = PlaylistsFragment()
    }
}
