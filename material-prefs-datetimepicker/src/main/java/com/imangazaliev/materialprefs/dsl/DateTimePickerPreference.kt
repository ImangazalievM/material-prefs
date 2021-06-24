package com.imangazaliev.materialprefs.dsl

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.dateTimePicker
import java.text.SimpleDateFormat
import java.util.*

@PreferencesMarker
class DateTimePickerPreference(
    key: String,
    container: PrefsContainer,
    appearanceManager: PreferencesAppearance
) : BaseDateTimePickerPreference<DateTimePickerPreference>(
    key = key,
    container = container,
    appearanceManager = appearanceManager
) {

    override val defaultFormatter = SimpleDateFormat("dd-M-yyyy hh:mm", Locale.US)
    private var minDateTime: Calendar? = null
    private var requireFutureDateTime = false
    private var show24HoursView = false
    private var autoFlipToTime: Boolean = true

    fun minDateTime(minDateTime: Calendar) {
        this.minDateTime = minDateTime
    }

    fun requireFutureDateTime(requireFutureDateTime: Boolean) {
        this.requireFutureDateTime = requireFutureDateTime
    }

    fun show24HoursView(show24HoursView: Boolean) {
        this.show24HoursView = show24HoursView
    }

    fun autoFlipToTime(autoFlipToTime: Boolean) {
        this.autoFlipToTime = autoFlipToTime
    }

    override fun showPickerDialog(value: Calendar) {
        MaterialDialog(context).show {
            dateTimePicker(
                currentDateTime = value,
                minDateTime = minDateTime,
                requireFutureDateTime = requireFutureDateTime,
                show24HoursView = show24HoursView,
                autoFlipToTime = autoFlipToTime
            ) { _, dateTime ->
                onSelected(dateTime)
            }
        }
    }

}