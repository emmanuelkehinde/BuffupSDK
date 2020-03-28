package com.emmanuelkehinde.buffup

import androidx.annotation.NonNull

/**
 * Created by Emmanuel Kehinde on 2020-03-27.
 */
class Buffup {

    internal var authKey: String? = null

    companion object {

        private var mInstance: Buffup? = null

        /**
         * Initializes SDK
         *
         * @param authKey The authentication key that is require by the SDK from the hosting application
         */
        fun initialize(@NonNull authKey: String) {
            mInstance = Buffup()
            mInstance?.authKey = authKey
        }

        /**
         * Returns the instance of the class
         * @return mInstance
         */
        internal fun getInstance(): Buffup? = mInstance
    }

}