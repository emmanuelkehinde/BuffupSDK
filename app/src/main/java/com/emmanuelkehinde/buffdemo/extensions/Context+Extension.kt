package com.emmanuelkehinde.buffdemo.extensions

import android.content.Context
import android.widget.Toast

/**
 * Created by Emmanuel Kehinde on 2020-03-29.
 */

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}