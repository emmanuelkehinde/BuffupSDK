package com.emmanuelkehinde.buffdemo.ui.stream_player

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.emmanuelkehinde.buffdemo.R
import com.emmanuelkehinde.buffup.listeners.EventListener
import com.emmanuelkehinde.buffup.model.Buff
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util.getUserAgent
import kotlinx.android.synthetic.main.activity_stream_player.*

class StreamPlayerActivity : AppCompatActivity() {

    private val VIDEO_URL = "https://buffup-public.s3.eu-west-2.amazonaws.com/video/toronto+nba+cut+3.mp4"
    private val APP_NAME = "BuffDemo"

    private val player: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Set to fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_stream_player)

        if (VIDEO_URL.isNotEmpty()) {
            prepareExoPlayer()
            initializeBuffView()
        }
    }

    private fun prepareExoPlayer() {
        val player = SimpleExoPlayer.Builder(this).build()
        playerView.player = player

        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(this,
            getUserAgent(this, APP_NAME))

        // This is the MediaSource representing the media to be played.
        val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(VIDEO_URL.toUri())

        // Prepare the player with the source.
        player.prepare(videoSource)
        player.playWhenReady = true

    }

    private fun initializeBuffView() {

        buffView.setupWithStream("12345")
        buffView.addListener(object : EventListener {
            override fun onBuffDisplayed(buff: Buff) {
                super.onBuffDisplayed(buff)
                print("Buff Displayed")
            }
        })
        buffView.start(this)

        try {
        } catch (e: Exception) {
            Log.e(this.localClassName, e.localizedMessage)
        }

    }

    override fun onStop() {
        player?.stop()
        player?.release()

        //Stop buffview
        buffView.stop()

        super.onStop()
    }
}
