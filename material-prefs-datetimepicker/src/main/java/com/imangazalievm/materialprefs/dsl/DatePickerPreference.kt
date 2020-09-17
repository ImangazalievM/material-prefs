package com.imangazalievm.materialprefs.dsl

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import java.text.SimpleDateFormat
import java.util.*

@PreferencesMarker
class DatePickerPreference(
    key: String,
    container: PrefsContainer,
    appearanceManager: PreferencesAppearance
) : BaseDateTimePickerPreference<DatePickerPreference>(
    key = key,
    container = container,
    appearanceManager = appearanceManager
) {

    override val defaultFormatter = SimpleDateFormat("dd MMMM yyyy", Locale.US)
    private var minDate: Calendar? = null
    private var maxDate: Calendar? = null
    private var requireFutureDate: Boolean = false

    fun minDate(minDate: Calendar) {
        this.minDate = minDate
    }

    fun maxDate(maxDate: Calendar) {
        this.maxDate = maxDate
    }

    fun requireFutureDate(requireFutureDate: Boolean) {
        this.requireFutureDate = requireFutureDate
    }

    override fun showPickerDialog(value: Calendar) {
        MaterialDialog(context).show {
            datePicker(
                currentDate = value,
                minDate = minDate,
                maxDate = maxDate,
                requireFutureDate = requireFutureDate
            ) { _, date ->
                onSelected(date)
            }
        }
    }

}