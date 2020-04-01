package com.emmanuelkehinde.buffdemo.ui.stream_player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emmanuelkehinde.buffdemo.helpers.VIDEO_URL

/**
 * Created by Emmanuel Kehinde on 2020-03-29.
 */
class StreamPlayerViewModel: ViewModel() {

    private var _videoUrl = MutableLiveData<String>()
    val videoUrl: LiveData<String>
        get() = _videoUrl

    fun fetchVideoUrl() {
        _videoUrl.value = VIDEO_URL
    }
}