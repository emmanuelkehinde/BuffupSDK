package com.emmanuelkehinde.buffup.remote

import com.emmanuelkehinde.buffup.model.Buff

/**
 * Created by Emmanuel Kehinde on 2020-03-28.
 */

internal sealed class BuffResult {
    data class Success(val buff: Buff) : BuffResult()
    data class Error(val error: Throwable) : BuffResult()
}