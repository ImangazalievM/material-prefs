package com.imangazaliev.materialprefs.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.cardview.widget.CardView
import com.imangazaliev.materialprefs.util.dpToPx
import com.imangazaliev.materialprefs.util.visible

class PreferenceCategoryView(
    context: Context,
    attrs: AttributeSet? = null,
    themeResId: Int = 0
) : CardView(context, attrs, themeResId) {

    private val container by lazy {
        LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }
    }
    private val title by lazy {
        TextView(context).apply {
            visible(false)
        }
    }

    init {
        useCompatPadding = true
        radius = 0f

        val containerLp = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        containerLp.setMargins(0, 12.dpToPx(context), 0, 12.dpToPx(context))
        addView(container, containerLp)

        title.setTypeface(title.typeface, Typeface.BOLD)
        title.textSize = 16f
        val titleLp = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        titleLp.marginStart = 16.dpToPx(context)
        titleLp.setMargins(0, 0, 0, 12.dpToPx(context))
        container.addView(title, titleLp)
    }

    fun setTitle(titleText: String) {
        title.visible(titleText.isNotEmpty())
        title.text = titleText
    }

    fun setTitleColor(@ColorInt color: Int) {
        title.setTextColor(color)
    }

    fun addPreferenceView(view: PreferenceView) {
        val preferenceLp = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        container.addView(view, preferenceLp)
    }

}