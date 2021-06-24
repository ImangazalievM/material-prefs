package com.imangazaliev.materialprefs.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.widget.ImageViewCompat

abstract class PreferenceView(
    context: Context,
    attrs: AttributeSet? = null,
    themeResId: Int = 0
) : LinearLayout(context, attrs, themeResId)