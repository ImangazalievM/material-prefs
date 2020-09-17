package com.imangazalievm.materialprefs.util

import com.imangazalievm.materialprefs.storage.PreferencesStorage
import kotlin.reflect.KClass

private val supportedTypes = listOf(
    String::class,
    Int::class,
    Long::class,
    Float::class,
    Boolean::class
)

fun checkIsTypeSupported(valueType: KClass<*>) {
    val isTypeSupported = supportedTypes.contains(valueType)
    if (!isTypeSupported) {
        throw IllegalArgumentException("This data type is not supported")
    }
}

fun <T : Any> PreferencesStorage.getValueWithType(key: String, valueType: KClass<T>): T {
    return when (valueType) {
        String::class -> getString(key) as T
        Int::class -> getInt(key) as T
        Long::class -> getLong(key) as T
        Float::class -> getFloat(key) as T
        Boolean::class -> getBoolean(key) as T
        else -> throw IllegalArgumentException("Type ${valueType.simpleName} is not primitive")
    }
}

fun <T : Any> PreferencesStorage.putValueWithType(key: String, value: T) {
    return when (value::class) {
        String::class -> putString(key, value as String)
        Int::class -> putInt(key, value as Int)
        Long::class -> putLong(key, value as Long)
        Float::class -> putFloat(key, value as Float)
        Boolean::class -> putBoolean(key, value as Boolean)
        else -> throw IllegalArgumentException("Type ${value::class.simpleName} is not primitive")
    }
}