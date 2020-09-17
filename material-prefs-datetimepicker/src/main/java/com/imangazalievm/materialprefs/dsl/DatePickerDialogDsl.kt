package com.imangazalievm.materialprefs.dsl

import com.imangazalievm.materialprefs.dsl.preferences.PreferenceBuilder

fun PreferenceCategory.datePicker(
    key: String,
    builder: PreferenceBuilder<DatePickerPreference>
) {
    DatePickerPreference(key, container, appearanceManager)
        .apply(builder)
        .also { addPreference(it) }
}

fun PreferenceCategory.timePicker(
    key: String,
    builder: PreferenceBuilder<TimePickerPreference>
) {
    TimePickerPreference(key, container, appearanceManager)
        .apply(builder)
        .also { addPreference(it) }
}

fun PreferenceCategory.dateTimePicker(
    key: String,
    builder: PreferenceBuilder<DateTimePickerPreference>
) {
    DateTimePickerPreference(key, container, appearanceManager)
        .apply(builder)
        .also { addPreference(it) }
}