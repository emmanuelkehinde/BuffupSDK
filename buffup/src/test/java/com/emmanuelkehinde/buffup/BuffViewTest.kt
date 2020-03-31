package com.emmanuelkehinde.buffup

import android.app.Activity
import com.emmanuelkehinde.buffup.exceptions.MissingInitializationException
import com.emmanuelkehinde.buffup.view.BuffView
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Emmanuel Kehinde on 2020-03-31.
 */

@RunWith(MockitoJUnitRunner::class)
class BuffViewTest {

    @Mock
    lateinit var activity: Activity

    @Mock
    lateinit var buffView: BuffView

    @Test
    fun buffViewThrowsInitializationExceptionWhenStartIsCalled() {
        try {
            buffView.start(activity)
        } catch (e: MissingInitializationException) {

        } catch (e: NullPointerException) {

        }
    }

}