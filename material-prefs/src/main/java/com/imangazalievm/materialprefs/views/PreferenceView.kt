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

abstract class PreferenceView(
    context: Context,
    attrs: AttributeSet? = null,
    themeResId: Int = 0
) : LinearLayout(context, attrs, themeResId)