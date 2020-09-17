package com.imangazalievm.materialprefs.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.imangazalievm.materialprefs.color.R
import com.imangazalievm.materialprefs.util.inflate

class ColorPreferenceView(
    context: Context,
    attrs: AttributeSet? = null,
    themeResId: Int = 0
) : BasePreferenceView(context, attrs, themeResId) {

    override fun createValueView(parent: ViewGroup): View {
        return parent.inflate(R.layout.pref_color)
    }

}