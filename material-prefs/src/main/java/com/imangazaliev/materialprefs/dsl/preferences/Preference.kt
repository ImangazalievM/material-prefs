package com.imangazaliev.materialprefs.dsl.preferences

import com.imangazaliev.materialprefs.dsl.PreferencesMarker
import com.imangazaliev.materialprefs.dsl.PrefsContainer
import com.imangazaliev.materialprefs.views.PreferenceView

typealias PreferenceBuilder<T> = T.() -> Unit

@PreferencesMarker
abstract class Preference<P, V : PreferenceView, T : Any>(
    protected val container: PrefsContainer,
    protected val key: String
) where P : Preference<P, V, T> {

    protected val context = container.context
    protected val storage = container.storage
    val view: V by lazy {
        createView()
            .apply { initView(this) }
            .apply { loadValue(this) }
    }

    protected abstract fun createView(): V

    protected abstract fun loadValue(view: V)

    protected open fun initView(view: V) {

    }

    protected fun valueString(): String? = storage.getString(key)

    protected fun valueInt(): Int = storage.getInt(key)

    protected fun valueLong(): Long = storage.getLong(key)

    protected fun valueFloat(): Float = storage.getFloat(key)

    protected fun valueBoolean(): Boolean = storage.getBoolean(key)

    protected fun saveValue(value: String) = storage.putString(key, value)

    protected fun saveValue(value: Int) = storage.putInt(key, value)

    protected fun saveValue(value: Long) = storage.putLong(key, value)

    protected fun saveValue(value: Float) = storage.putFloat(key, value)

    protected fun saveValue(value: Boolean) = storage.putBoolean(key, value)

}