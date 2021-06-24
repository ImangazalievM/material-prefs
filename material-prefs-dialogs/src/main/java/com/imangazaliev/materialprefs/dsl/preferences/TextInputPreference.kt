package com.imangazaliev.materialprefs.dsl.preferences

import androidx.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.imangazaliev.materialprefs.dsl.PreferencesAppearance
import com.imangazaliev.materialprefs.dsl.PreferencesMarker
import com.imangazaliev.materialprefs.dsl.PrefsContainer
import com.imangazaliev.materialprefs.views.TextInputPreferenceView

@PreferencesMarker
class TextInputPreference(
    key: String,
    container: PrefsContainer,
    appearanceManager: PreferencesAppearance
) : BasePreference<TextInputPreference, TextInputPreferenceView, Unit>(
    key = key,
    container = container,
    appearanceManager = appearanceManager
) {

    private var dialogTitle: String? = null
    private var hintText: String? = null
    private var saveButtonLabel: String? = null
    private var onNewInputListener: ((String) -> Unit)? = null

    fun dialogTitle(@StringRes titleResId: Int) {
        dialogTitle(context.getString(titleResId))
    }

    fun dialogTitle(dialogTitle: String) {
        this.dialogTitle = dialogTitle
    }

    fun hint(@StringRes hintResId: Int) {
        hint(context.getString(hintResId))
    }

    fun hint(hintText: String) {
        this.hintText = hintText
    }

    fun saveButtonLabel(@StringRes labelResId: Int) {
        saveButtonLabel(context.getString(labelResId))
    }

    fun saveButtonLabel(label: String) {
        this.saveButtonLabel = label
    }

    fun onNewInput(listener: (String) -> Unit) {
        this.onNewInputListener = listener
    }

    override fun createView(): TextInputPreferenceView {
        return TextInputPreferenceView(context)
    }

    override fun loadValue(view: TextInputPreferenceView) {
        view.setValueText(valueString())
    }

    override fun initView(view: TextInputPreferenceView) {
        super.initView(view)

        view.setOnClickListener {
            val initialValue = valueString()
            MaterialDialog(context).show {
                title(text = dialogTitle ?: title)
                input(
                    hint = hintText,
                    prefill = initialValue
                ) { _, text ->
                    val value = text.toString()
                    saveValue(value)
                    view.setValueText(value)
                    onNewInputListener?.invoke(value)
                }
                positiveButton(text = saveButtonLabel)
            }
        }
    }

}