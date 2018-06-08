package com.example.tomjangtestgg.youtuebeplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_standalone.*

class StandaloneActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "standaloneActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        Log.d(TAG, "starting standaloneActivity")

        btnPlayVideo.setOnClickListener(this)
        btnPlaylist.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val intent = when (view.id){
            R.id.btnPlayVideo -> YouTubeStandalonePlayer.createVideoIntent(
                    this, getString(R.string.GOOGLE_API_KEY), "KMX1mFEmM3E",0,true, false
            )
            R.id.btnPlaylist -> YouTubeStandalonePlayer.createPlaylistIntent(
                    this, getString(R.string.GOOGLE_API_KEY), "PL55RiY5tL51r5jyQoPZhwLueLpPeAV6P9",0,0,true,true
            )
            else -> throw IllegalArgumentException("Undefined button clicked")
        }
        //start another activity
        startActivity(intent)

    }
}