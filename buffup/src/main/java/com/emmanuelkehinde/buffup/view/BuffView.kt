package com.emmanuelkehinde.buffup.view

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.NonNull
import com.emmanuelkehinde.buffup.Buffup
import com.emmanuelkehinde.buffup.R
import com.emmanuelkehinde.buffup.exceptions.MissingInitializationException
import com.emmanuelkehinde.buffup.listeners.EventListener
import com.emmanuelkehinde.buffup.model.Buff

/**
 * Created by Emmanuel Kehinde on 2020-03-27.
 */
class BuffView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    /** View Attributes */
    var authorInfoBackground: Drawable? = null
        set(value) {
            field = value
            refreshView()
        }

    /** Event Listener to be implemented by the host app */
    private var listener: EventListener? = null

    /** Whether Buffs should be fetched and displayed or not */
    private var shouldFetchAndDisplayBuffs = false

    /** The ID of the present stream from the host application */
    private var streamId: String? = null

    /** Instance of the BuffView */
    private val buffView: LinearLayout?

    /** Current ID of a buff to be fetched */
    private var currentBuffId: Int = 1

    /** Maximum available ID for buffs */
    private var maxBuffId: Int = 5

    /**
     * Initialize view and get view attributes
     */
    init {
        buffView = View.inflate(context, R.layout.buff_view, this) as LinearLayout
        context.theme.obtainStyledAttributes(attrs, R.styleable.BuffView,
            0, 0).apply {

            try {
                authorInfoBackground = getDrawable(R.styleable.BuffView_buff_author_info_bg)
            } finally {
                recycle()
            }
        }

        buffView.visibility = View.GONE
    }

    /**
     * Redraws the view
     */
    private fun refreshView() {
        invalidate()
        requestLayout()
    }

    /**
     * Set constraints and alignments for the buffview and make it show proportionately on every screen
     * */
    private fun applyAlignmentConfigurationToBuffView() {
        /* Set BuffView width to be 40% of the screen width*/
        val width = (Resources.getSystem().displayMetrics.widthPixels * 0.4).toInt()
        val viewParams = RelativeLayout.LayoutParams(
            width, RelativeLayout.LayoutParams.WRAP_CONTENT
        )

        /* Add margins */
        val margin = resources.getDimension(R.dimen.buff_buffview_layout_margin).toInt()
        viewParams.setMargins(margin, margin, margin, margin)

        viewParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1)
        buffView?.layoutParams = viewParams
    }

    /**
     * Connects the BuffView to the present Stream in the host application
     *
     * <p>For the actual use case of the SDK, Buffs are fetched using the ID of the stream currently
     * playing and are then displayed at interval if the host app has the start method called.
     *
     * @param streamId The ID of the present stream playing
     */
    fun setupWithStream(@NonNull streamId: String) {
        this.streamId = streamId
    }

    /**
     * Starts fetching of Buffs and displaying them
     *
     * @param activity Activity on which BuffView is hosted on
     * @throws MissingInitializationException
     */
    @Throws(MissingInitializationException::class)
    fun start(@NonNull activity: Activity) {
        shouldFetchAndDisplayBuffs = true
        fetchBuffWithId(currentBuffId, activity)
    }

    /**
     * Fetches a buff using its ID
     *
     * @param buffId ID of the Buff to be fetched
     * @param activity Activity on which BuffView is hosted on
     */
    private fun fetchBuffWithId(buffId: Int, activity: Activity) {
        /* Stop fetching buffs when maximum Buff ID is reached */
        if (buffId > maxBuffId) return



        //On Response
//        displayBuffView(buff, activity)
        Handler().postDelayed({
            fetchBuffWithId(++currentBuffId, activity)
        }, TIME_BETWEEN_BUFFS)
    }

    /**
     * Displays BuffView after fetching
     *
     * @param buff The present Buff
     * @param activity Activity on which BuffView is hosted on
     *
     * @throws MissingInitializationException
     */
    private fun displayBuffView(buff: Buff, activity: Activity) {
        /* Check if user wants Buffs to be fetched */
        if (!shouldFetchAndDisplayBuffs) return

        /* Check if Buffup is initialized */
        checkForBuffupInitialization()

        /* Apply alignment configuration*/
        applyAlignmentConfigurationToBuffView()


        buffView?.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(buffView?.context, R.anim.buff_slide_right)
        buffView?.startAnimation(animation)

    }

    /**
     * Checks if Buffup has been initialized
     *
     * @throws MissingInitializationException
     */
    private fun checkForBuffupInitialization() {
        if (Buffup.getInstance()?.authKey == null) {
            throw MissingInitializationException(
                Throwable(context.getString(R.string.buff_missing_initialization_exception))
            )
        }
    }

    /**
     * Stops fetching and displaying of Buffs. Also removes listener automatically
     */
    fun stop() {
        shouldFetchAndDisplayBuffs = false
        removeListener()
    }

    /**
     * Adds an event listener to the view to notify the host app of events happening within the view
     *
     * @param listener The EventListener interface that is to be implemented
     */
    fun addListener(@NonNull listener: EventListener) {
        this.listener = listener
    }

    /**
     * Removes the event listener from the view to stop notifying the host app of events happening
     * within the vie
     */
    fun removeListener() {
        this.listener?.let {
            this.listener == null
        }
    }


    /** Provide programmatic setting of BuffView attributes using the Builder pattern */

    /**
     * Set background drawable for the author information view
     */
    fun setAuthorInfoBackground(@NonNull value: Drawable): BuffView {
        authorInfoBackground = value
        return this
    }

    companion object {
        private const val DEFAULT_AUTHOR_INFO_BACKGROUND = Color.BLACK


        private const val TIME_BETWEEN_BUFFS = 30000L
    }
}