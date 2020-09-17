package com.imangazalievm.materialprefs.views.simple

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.imangazalievm.materialprefs.R
import com.imangazalievm.materialprefs.util.inflate
import com.imangazalievm.materialprefs.views.BasePreferenceView
import kotlinx.android.synthetic.main.pref_label.view.*

class LabelPreferenceView(
    context: Context,
    attrs: AttributeSet? = null,
    themeResId: Int = 0
) : BasePreferenceView(context, attrs, themeResId) {

    override fun createValueView(parent: ViewGroup): View {
        return parent.inflate(R.layout.pref_label)
    }

    fun setLabelText(text: String?) {
        this.valueText.text = text
    }

}