package com.imangazaliev.materialprefs.dsl

import com.imangazaliev.materialprefs.dsl.preferences.PreferenceBuilder

fun PreferenceCategory.colorPicker(
    key: String,
    builder: PreferenceBuilder<ColorPickerPreference>
) {
    ColorPickerPreference(key, container, appearanceManager)
        .apply(builder)
        .also { addPreference(it) }
}