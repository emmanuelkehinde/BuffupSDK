package com.emmanuelkehinde.buffdemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.emmanuelkehinde.buffdemo.helpers.VIDEO_URL
import com.emmanuelkehinde.buffdemo.ui.stream_player.StreamPlayerViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


/**
 * Created by Emmanuel Kehinde on 2020-03-31.
 */
@RunWith(MockitoJUnitRunner::class)
class StreamPlayerViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<String>

    private lateinit var streamPlayerViewModel: StreamPlayerViewModel

    @Before
    fun setUp() {
        streamPlayerViewModel = StreamPlayerViewModel()
    }

    @Test
    fun `videoUrl has an observer`() {
        streamPlayerViewModel.videoUrl.observeForever(observer)
        assert(streamPlayerViewModel.videoUrl.hasObservers())
    }

    @Test
    fun `fetchVideoUrl sets correct value for videoUrl`() {
        streamPlayerViewModel.fetchVideoUrl()
        assert(streamPlayerViewModel.videoUrl.value == VIDEO_URL)
    }

    @Test
    fun `fetchVideoUrl should trigger the observer for videoUrl`() {
        streamPlayerViewModel.videoUrl.observeForever(observer)
        streamPlayerViewModel.fetchVideoUrl()
        verify(observer).onChanged(VIDEO_URL)
    }
}