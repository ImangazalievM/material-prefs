package com.imangazaliev.materialprefs.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.imangazaliev.materialprefs.R
import com.imangazaliev.materialprefs.util.inflate

class TextInputPreferenceView(
    context: Context,
    attrs: AttributeSet? = null,
    themeResId: Int = 0
) : BasePreferenceView(context, attrs, themeResId) {

    override fun createValueView(parent: ViewGroup): View {
        return parent.inflate(R.layout.pref_label)
    }

    fun setValueText(text: String?) {
        val label = findViewById<TextView>(R.id.valueText)
        label.text = text
    }

}