package com.emmanuelkehinde.buffup.exceptions

/**
 * Created by Emmanuel Kehinde on 2020-03-28.
 */

/**
 * Thrown when Buffup is not initialized
 */
class MissingInitializationException(throwable: Throwable): Exception(throwable)

/**
 * Thrown when parent view is not supported
 */
class UnsupportedActivityViewGroupException(throwable: Throwable): Exception(throwable)