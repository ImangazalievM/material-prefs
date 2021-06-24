package com.imangazaliev.materialprefs.dsl

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.timePicker
import java.text.SimpleDateFormat
import java.util.*

@PreferencesMarker
class TimePickerPreference(
    key: String,
    container: PrefsContainer,
    appearanceManager: PreferencesAppearance
) : BaseDateTimePickerPreference<TimePickerPreference>(
    key = key,
    container = container,
    appearanceManager = appearanceManager
) {

    override val defaultFormatter = SimpleDateFormat("hh:mm", Locale.US)
    private var requireFutureTime: Boolean = false
    private var show24HoursView: Boolean = true

    fun requireFutureTime(requireFutureTime: Boolean) {
        this.requireFutureTime = requireFutureTime
    }

    fun show24HoursView(show24HoursView: Boolean) {
        this.show24HoursView = show24HoursView
    }

    override fun showPickerDialog(value: Calendar) {
        MaterialDialog(context).show {
            timePicker(
                currentTime = value,
                requireFutureTime = requireFutureTime,
                show24HoursView = show24HoursView
            ) { _, time ->
                onSelected(time)
            }
        }
    }

}