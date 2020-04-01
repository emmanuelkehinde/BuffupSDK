package com.emmanuelkehinde.buffdemo.ui.stream_player

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.emmanuelkehinde.buffdemo.R
import com.emmanuelkehinde.buffdemo.extensions.showToast
import com.emmanuelkehinde.buffup.listeners.EventListener
import com.emmanuelkehinde.buffup.model.Buff
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util.getUserAgent
import kotlinx.android.synthetic.main.activity_stream_player.*

class StreamPlayerActivity : AppCompatActivity() {

    private val player: SimpleExoPlayer? = null

    private val streamPlayerViewModel: StreamPlayerViewModel by lazy {
        ViewModelProviders.of(this).get(StreamPlayerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Set to fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_stream_player)
        //Fetch the url of the video to be viewed
        streamPlayerViewModel.fetchVideoUrl()

        streamPlayerViewModel.videoUrl.observe(this, Observer {
            if (it.isNotEmpty()) {
                prepareExoPlayer(it)
                initializeBuffView()
            }
        })

    }

    private fun prepareExoPlayer(videoUrl: String) {
        val player = SimpleExoPlayer.Builder(this).build()
        playerView.player = player

        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(this,
            getUserAgent(this, getString(R.string.app_name)))

        // This is the MediaSource representing the media to be played.
        val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(videoUrl.toUri())

        // Prepare the player with the source.
        player.prepare(videoSource)
        player.playWhenReady = true

    }

    private fun initializeBuffView() {
        buffView.setupWithStream("STREAM_ID")
        buffView.addListener(object : EventListener {
            override fun onBuffDisplayed(buff: Buff) {
                super.onBuffDisplayed(buff)
                Log.d(this.javaClass.simpleName,"Buff Displayed")
            }

            override fun onBuffAnswerSelected(answer: Buff.Answer) {
                super.onBuffAnswerSelected(answer)
                showToast("Answer selected: ${answer.title}")
            }

            override fun onBuffError(t: Throwable) {
                super.onBuffError(t)
                showToast("Error: ${t.localizedMessage}")
            }
        })
        buffView.start(this)

    }

    override fun onStop() {
        player?.stop()
        player?.release()

        //Stop BuffView
        buffView.stop()

        super.onStop()
    }
}
