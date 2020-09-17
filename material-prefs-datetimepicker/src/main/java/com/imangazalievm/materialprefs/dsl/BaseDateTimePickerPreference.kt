package com.imangazalievm.materialprefs.dsl

import android.widget.TextView
import com.imangazalievm.materialprefs.R
import com.imangazalievm.materialprefs.dsl.preferences.BasePreference
import com.imangazalievm.materialprefs.views.simple.LabelPreferenceView
import java.text.SimpleDateFormat
import java.util.*

@PreferencesMarker
abstract class BaseDateTimePickerPreference<P>(
    key: String,
    container: PrefsContainer,
    appearanceManager: PreferencesAppearance
) : BasePreference<P, LabelPreferenceView, Unit>(
    key = key,
    container = container,
    appearanceManager = appearanceManager
) where P : BaseDateTimePickerPreference<P> {

    private var valueConverter: ((Calendar) -> String)? = null
    protected var onValueSelected: ((Calendar) -> Unit)? = null
    protected abstract val defaultFormatter: SimpleDateFormat

    fun valuePresenter(converter: (Calendar) -> String) {
        this.valueConverter = converter
    }

    fun onValueSelected(listener: (Calendar) -> Unit) {
        this.onValueSelected = listener
    }

    override fun createView(): LabelPreferenceView {
        return LabelPreferenceView(context)
    }

    override fun loadValue(view: LabelPreferenceView) {
        val value = getValue()
        val text = valueConverter?.invoke(value) ?: defaultFormatter.format(value.time)
        view.setLabelText(text)
    }

    override fun initView(view: LabelPreferenceView) {
        super.initView(view)

        view.setOnClickListener {
            showPickerDialog(getValue())
        }
    }

    private fun getValue(): Calendar {
        val calendar = GregorianCalendar.getInstance()
        calendar.timeInMillis = valueLong()
        return calendar
    }

    protected fun onSelected(value: Calendar) {
        saveValue(value.timeInMillis)
        onValueSelected?.invoke(value)
    }

    protected abstract fun showPickerDialog(value: Calendar)

}