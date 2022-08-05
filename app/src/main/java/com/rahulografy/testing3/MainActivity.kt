package com.rahulografy.testing3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rahulografy.testing3.playlists.PlaylistsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.cl_parent, PlaylistsFragment.newInstance())
                .commit()
        }
    }
}
