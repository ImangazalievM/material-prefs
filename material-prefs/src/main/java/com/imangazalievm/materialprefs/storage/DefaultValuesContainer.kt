package com.imangazalievm.materialprefs.storage

class DefaultValuesContainer(
    private val values: Map<String, Any>
) {

    fun getString(key: String): String? {
        return values[key] as String?
    }

    fun getInt(key: String): Int {
        return values[key] as Int
    }

    fun getLong(key: String): Long {
        return values[key] as Long
    }

    fun getFloat(key: String): Float {
        return values[key] as Float
    }

    fun getBoolean(key: String): Boolean {
        return values[key] as Boolean
    }

    class Builder {

        private val values = mutableMapOf<String, Any>()

        infix fun String.to(value: String) {
            values[this] = value
        }

        infix fun String.to(value: Int) {
            values[this] = value
        }

        infix fun String.to(value: Long) {
            values[this] = value
        }

        infix fun String.to(value: Float) {
            values[this] = value
        }

        infix fun String.to(value: Boolean) {
            values[this] = value
        }

        internal fun build(): DefaultValuesContainer {
            return DefaultValuesContainer(values)
        }

    }

}