package com.imangazalievm.materialprefs.storage

interface PreferencesStorage {

  fun putString(key: String, value: String?)
  
  fun getString(key: String): String?
  
  fun putInt(key: String, value: Int)
  
  fun getInt(key: String): Int
  
  fun putLong(key: String, value: Long)
  
  fun getLong(key: String): Long
  
  fun putFloat(key: String, value: Float)
  
  fun getFloat(key: String): Float

  fun putBoolean(key: String, value: Boolean)
  
  fun getBoolean(key: String): Boolean

}
