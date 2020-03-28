package com.emmanuelkehinde.buffup.listeners

import com.emmanuelkehinde.buffup.model.Buff

/**
 * Created by Emmanuel Kehinde on 2020-03-27.
 */

///**
// * A BuffView view interface defining all basic functionality, such as answering a question from and
// * closing of the currently displayed BuffView.
// */
//interface BuffView {
//
//    fun closeBuff() {}

    /**
     * Listener of changes in the view. All methods have default implementations to allow
     * selective overrides.
     */
    interface EventListener {

        /**
         * Called when a buff is displayed on the screen.
         *
         * @param buff The buff displayed.
         */
        fun onBuffDisplayed(buff: Buff) {}
    }


//}
