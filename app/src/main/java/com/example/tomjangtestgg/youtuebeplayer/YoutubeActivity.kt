package com.example.tomjangtestgg.youtuebeplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

//const val YOUTUBE_VIDEO_ID = "KMX1mFEmM3E"
//const val YOUTBUE_PLAYLIST = "PL55RiY5tL51r5jyQoPZhwLueLpPeAV6P9"

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private val TAG = "YoutubeActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_youtube)
        //val layout = findViewById<ConstraintLayout>(R.id.activity_youtube)
        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)


        /*
        val button1 = Button(this)
        button1.layoutParams = ConstraintLayout.LayoutParams (600, 180)
        button1.text = "Button added"
        layout.addView(button1)
        */


        // add playerview to youtubeActovoty
        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY),this)

    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, wasRestored: Boolean) {

        Log.d(TAG, "onInitialziationSuccess: provider is ${provider?.javaClass}")
        Log.d(TAG, "onInitialziationSuccess: youtubePlayer is ${youTubePlayer?.javaClass}")
        Toast.makeText(this, "Initialize Youtube Player successfully", Toast.LENGTH_SHORT).show()

        youTubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)
        youTubePlayer?.setPlaybackEventListener(playbackEventListener)
        if(!wasRestored){
            youTubePlayer?.cueVideo("KMX1mFEmM3E")
        } else{
            youTubePlayer?.play()
        }

    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, youTubeInitializationResult: YouTubeInitializationResult?) {
        val REQUEST_CODE = 0

        if(youTubeInitializationResult?.isUserRecoverableError == true){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show()
        }else{
            val errorMessage = "There was an error initialization the youtubeplayer ($youTubeInitializationResult)"
            Toast.makeText(this, errorMessage,Toast.LENGTH_LONG).show()
        }
    }

    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener{
        override fun onSeekTo(p0: Int) {

        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity,"Good, video is playing ok", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {

        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity,"Video has paused", Toast.LENGTH_SHORT).show()
        }
    }

    private val playerStateChangeListener = object : YouTubePlayer.PlayerStateChangeListener{
        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity,"Click Ad now, make the video creator rich!", Toast.LENGTH_SHORT).show()

        }

        override fun onLoading() {
        }

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity,"Video has started", Toast.LENGTH_SHORT).show()

        }

        override fun onLoaded(p0: String?) {
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity,"You've completed another video", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }
    }
}