package com.imangazaliev.materialprefs.storage

import android.content.SharedPreferences

open class DefaultPreferencesStorage(
    private val defaultValues: DefaultValuesContainer,
    private val preferences: SharedPreferences
) : PreferencesStorage {



    override fun putString(key: String, value: String?) {
        preferences.edit {
            putString(key, value)
        }
    }

    override fun getString(key: String): String? {
       return preferences.getString(key, defaultValues.getString(key))
    }

    override fun putInt(key: String, value: Int) {
        preferences.edit {
            putInt(key, value)
        }
    }

    override fun getInt(key: String): Int {
        return preferences.getInt(key, defaultValues.getInt(key))
    }

    override fun putLong(key: String, value: Long) {
        preferences.edit {
            putLong(key, value)
        }
    }

    override fun getLong(key: String): Long {
        return preferences.getLong(key, defaultValues.getLong(key))
    }

    override fun putFloat(key: String, value: Float) {
        return preferences.edit {
            putFloat(key, value)
        }
    }

    override fun getFloat(key: String): Float {
        return preferences.getFloat(key, defaultValues.getFloat(key))
    }

    override fun putBoolean(key: String, value: Boolean) {
        preferences.edit {
            putBoolean(key, value)
        }
    }

    override fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, defaultValues.getBoolean(key))
    }

    fun SharedPreferences.edit(body: SharedPreferences.Editor.() -> Unit) {
        val editor = this.edit()
        editor.body()
        editor.apply()
    }

}