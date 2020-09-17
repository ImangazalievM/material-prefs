package com.imangazalievm.materialprefs.views.checkable

import android.content.Context
import android.util.AttributeSet
import android.widget.CompoundButton
import com.imangazalievm.materialprefs.views.BasePreferenceView

abstract class CheckablePreferenceView(
    context: Context,
    attrs: AttributeSet? = null,
    themeResId: Int = 0
) : BasePreferenceView(context, attrs, themeResId) {

    abstract val checkableView: CompoundButton

    fun setChecked(isChecked: Boolean) {
        checkableView.isChecked = isChecked
    }

    fun onCheckedChanged(listener: (isChecked: Boolean) -> Unit) {
        checkableView.setOnCheckedChangeListener { _, isChecked ->
            listener(isChecked)
        }
    }

    abstract fun setColor(color: Int)

}