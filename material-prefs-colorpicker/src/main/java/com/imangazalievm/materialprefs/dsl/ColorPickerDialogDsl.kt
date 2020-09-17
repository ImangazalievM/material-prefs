package com.imangazalievm.materialprefs.dsl

import com.imangazalievm.materialprefs.dsl.preferences.PreferenceBuilder

fun PreferenceCategory.colorPicker(
    key: String,
    builder: PreferenceBuilder<ColorPickerPreference>
) {
    ColorPickerPreference(key, container, appearanceManager)
        .apply(builder)
        .also { addPreference(it) }
}