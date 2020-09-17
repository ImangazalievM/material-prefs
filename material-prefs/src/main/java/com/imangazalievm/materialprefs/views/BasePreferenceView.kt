package com.imangazalievm.materialprefs.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.widget.ImageViewCompat
import com.imangazalievm.materialprefs.R
import com.imangazalievm.materialprefs.storage.PreferencesStorage
import com.imangazalievm.materialprefs.util.dpToPx
import com.imangazalievm.materialprefs.util.getResIdFromAttribute
import com.imangazalievm.materialprefs.util.inflate
import com.imangazalievm.materialprefs.util.visible
import kotlinx.android.synthetic.main.base_preference.view.*

abstract class BasePreferenceView(
    context: Context,
    attrs: AttributeSet? = null,
    themeResId: Int = 0
) : LinearLayout(context, attrs, themeResId) {

    private lateinit var storage: PreferencesStorage

    init {
        orientation = HORIZONTAL
        setBackgroundResource(context.getResIdFromAttribute(android.R.attr.selectableItemBackground))
        val horizontalPadding = 16.dpToPx(context)
        val verticalPadding = 16.dpToPx(context)
        setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
        gravity = Gravity.CENTER_VERTICAL
        isClickable = true

        inflate(R.layout.base_preference, true)
        prefValueContainer.addView(createValueView(prefValueContainer))
    }

    internal fun setStorage(storage: PreferencesStorage) {
        this.storage = storage
    }

    fun setTitleColor(color: Int) {
        prefTitle.setTextColor(color)
    }

    fun setSummaryColor(color: Int) {
        prefSummary.setTextColor(color)
    }

    fun setIconColor(iconsColor: Int) {
        ImageViewCompat.setImageTintList(prefIcon, ColorStateList.valueOf(iconsColor))
    }

    fun setTitle(title: String?) {
        prefTitle.text = title
    }

    fun getTitle(title: String) {
        prefTitle.text = title
    }

    fun setSummary(summary: String?) {
        prefSummary.visible(summary != null)
        prefSummary.text = summary
    }

    fun getSummary(summary: String) {
        prefSummary.text = summary
    }

    fun setIcon(icon: Drawable?) {
        prefIcon.visible(icon != null)
        prefIcon.setImageDrawable(icon)
    }

    abstract fun createValueView(parent: ViewGroup): View

}