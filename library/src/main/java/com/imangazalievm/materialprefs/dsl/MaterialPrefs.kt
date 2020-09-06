package com.imangazalievm.materialprefs.dsl

import android.content.Context
import android.content.SharedPreferences
import com.imangazalievm.materialprefs.storage.DefaultPreferencesStorage
import com.imangazalievm.materialprefs.storage.DefaultValuesContainer
import com.imangazalievm.materialprefs.storage.PreferencesStorage
import com.imangazalievm.materialprefs.views.MaterialPreferencesView

fun prefs(
    view: MaterialPreferencesView,
    storage: PreferencesStorage,
    builder: PreferencesBuilder
): MaterialPreferences {
    return MaterialPreferences(PrefsContainer(view, storage))
        .apply(builder)
        .apply { init() }
}

fun Context.defaultPrefsStorage(
    prefsKey: String,
    defaultValues: DefaultValuesContainer
): DefaultPreferencesStorage {
    val sharedPreferences = getSharedPreferences(prefsKey, Context.MODE_PRIVATE)
    return DefaultPreferencesStorage(defaultValues, sharedPreferences)
}

fun Context.defaultPrefsStorage(
    sharedPreferences: SharedPreferences,
    defaultValues: DefaultValuesContainer
): DefaultPreferencesStorage {
    return DefaultPreferencesStorage(defaultValues, sharedPreferences)
}

fun defaultPrefValues(
    builder: DefaultValuesContainer.Builder.() -> Unit
): DefaultValuesContainer {
    return DefaultValuesContainer.Builder()
        .apply(builder)
        .build()
}