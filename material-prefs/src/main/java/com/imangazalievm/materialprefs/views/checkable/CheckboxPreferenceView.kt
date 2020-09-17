package com.imangazalievm.materialprefs.views.checkable

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.imangazalievm.materialprefs.R
import com.imangazalievm.materialprefs.util.color
import com.imangazalievm.materialprefs.util.drawable
import com.imangazalievm.materialprefs.util.inflate
import com.imangazalievm.materialprefs.util.tint
import kotlinx.android.synthetic.main.pref_checkbox.view.*

class CheckboxPreferenceView(
    context: Context,
    attrs: AttributeSet? = null,
    themeResId: Int = 0
) : CheckablePreferenceView(context, attrs, themeResId) {

    override val checkableView: CompoundButton by lazy {
        prefCheckbox
    }

    override fun createValueView(parent: ViewGroup): View {
        return parent.inflate(R.layout.pref_checkbox)
    }

    override fun setColor(color: Int) {
        val useDarker = false
        val disabledColor = context.color(
            if (useDarker) R.color.mpf_control_disabled_dark
            else R.color.mpf_control_disabled_light
        )
        val defaultColor = context.color(
            if (useDarker) R.color.mpf_control_normal_dark
            else R.color.mpf_control_normal_light
        )
        val sl = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
            ),
            intArrayOf(
                disabledColor,
                defaultColor,
                color
            )
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            prefCheckbox.buttonTintList = sl
        } else {
            @SuppressLint("PrivateResource")
            prefCheckbox.buttonDrawable = context.drawable(R.drawable.abc_btn_check_material)
                .tint(sl)
        }
    }

}