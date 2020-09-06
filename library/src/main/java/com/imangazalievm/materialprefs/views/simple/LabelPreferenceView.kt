package com.imangazalievm.materialprefs.views.simple

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.imangazalievm.materialprefs.views.BasePreferenceView

class LabelPreferenceView(
    context: Context,
    attrs: AttributeSet? = null,
    themeResId: Int = 0
) : BasePreferenceView(context, attrs, themeResId) {

    override fun createValueView(parent: ViewGroup): View {
        return TextView(context)
    }

}