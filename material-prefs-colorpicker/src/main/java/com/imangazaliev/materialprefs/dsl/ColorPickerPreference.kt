package com.imangazaliev.materialprefs.dsl

import android.R.string
import android.graphics.Color
import android.widget.TextView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.color.colorChooser
import com.imangazaliev.materialprefs.R
import com.imangazaliev.materialprefs.dsl.preferences.BasePreference
import com.imangazaliev.materialprefs.views.ColorPreferenceView
import kotlinx.android.synthetic.main.pref_color.view.*

@PreferencesMarker
class ColorPickerPreference(
    key: String,
    container: PrefsContainer,
    appearanceManager: PreferencesAppearance
) : BasePreference<ColorPickerPreference, ColorPreferenceView, String>(
    key = key,
    container = container,
    appearanceManager = appearanceManager
) {

    private lateinit var colors: IntArray
    private var dialogTitle: String? = null
    private var selectButtonLabel: String? = null
    private var onColorSelected: ((PrefColor) -> Unit)? = null

    fun dialogTitle(dialogTitle: String) {
        this.dialogTitle = dialogTitle
    }

    fun selectButtonLabel(selectLabel: String) {
        this.selectButtonLabel = selectLabel
    }

    fun colors(colors: IntArray) {
        this.colors = colors
    }

    fun onColorSelected(listener: (PrefColor) -> Unit) {
        this.onColorSelected = listener
    }

    override fun createView(): ColorPreferenceView {
        return ColorPreferenceView(context)
    }

    override fun loadValue(view: ColorPreferenceView) {
        val color = getCurrentColor()
        val prefSummary = view.findViewById<TextView>(R.id.prefSummary)
        prefSummary.text = color.asHexColor()
        view.colorView.color = color.asIntColor()
    }

    override fun initView(view: ColorPreferenceView) {
        super.initView(view)

        view.setOnClickListener {
            showColorChooseDialog()
        }
    }

    private fun getCurrentColor(): PrefColor {
        val color = valueString()
        return PrefColor(Color.parseColor(color))
    }

    private fun showColorChooseDialog() {
        MaterialDialog(context).show {
            title(text = dialogTitle ?: title)

            val color = getCurrentColor()
            colorChooser(colors, initialSelection = color.asIntColor()) { _, color ->
                this@ColorPickerPreference.view.colorView.color = color
                val prefColor = PrefColor(color)
                saveValue(prefColor.asHexColor())
                onColorSelected?.invoke(prefColor)
            }
            val selectButtonLabel = selectButtonLabel ?: context.getString(string.ok)
            positiveButton(text = selectButtonLabel)
        }
    }

}