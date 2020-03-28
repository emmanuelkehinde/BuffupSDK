package com.emmanuelkehinde.buffdemo

import android.app.Application
import com.emmanuelkehinde.buffup.Buffup

/**
 * Created by Emmanuel Kehinde on 2020-03-27.
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Buffup.initialize("")
    }
}