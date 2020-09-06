package com.imangazalievm.materialprefs.views.checkable

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.imangazalievm.materialprefs.R
import com.imangazalievm.materialprefs.util.adjustAlpha
import com.imangazalievm.materialprefs.util.inflate
import com.imangazalievm.materialprefs.util.modifySwitchDrawable
import kotlinx.android.synthetic.main.pref_switch.view.*

class SwitchPreferenceView(
    context: Context,
    attrs: AttributeSet? = null,
    themeResId: Int = 0
) : CheckablePreferenceView(context, attrs, themeResId) {

    override val checkableView: CompoundButton by lazy {
        prefSwitch
    }

    override fun createValueView(parent: ViewGroup): View {
        return parent.inflate(R.layout.pref_switch)
    }

    override fun setColor(color: Int) {
        if (prefSwitch.trackDrawable != null) {
            prefSwitch.trackDrawable = prefSwitch.trackDrawable.modifySwitchDrawable(
                context = context,
                requestedTint = color.adjustAlpha(0.5f),
                thumb = false,
                compatSwitch = false,
                useDarker = false
            )
        }
        if (prefSwitch.thumbDrawable != null) {
            prefSwitch.thumbDrawable = prefSwitch.thumbDrawable.modifySwitchDrawable(
                context = context,
                requestedTint = color,
                thumb = true,
                compatSwitch = false,
                useDarker = false
            )
        }
    }



}