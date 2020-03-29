package com.emmanuelkehinde.buffdemo.ui.stream_player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Emmanuel Kehinde on 2020-03-29.
 */
class StreamPlayerViewModel: ViewModel() {

    private var _videoUrl = MutableLiveData<String>()
    val videoUrl: LiveData<String>
        get() = _videoUrl

    init {
        getVideoUrl()
    }

    private fun getVideoUrl() {
        _videoUrl.value = "https://buffup-public.s3.eu-west-2.amazonaws.com/video/toronto+nba+cut+3.mp4"
    }
}