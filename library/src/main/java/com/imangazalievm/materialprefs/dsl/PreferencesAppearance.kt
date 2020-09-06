package com.imangazalievm.materialprefs.dsl

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.util.TypedValue
import com.imangazalievm.materialprefs.R
import com.imangazalievm.materialprefs.util.color


class PreferencesAppearance(
    private val context: Context
) {

    private var categoryTitleColor: Int? = null
    private var accentColor: Int? = null
    private var primaryTextColor: Int? = null
    private var secondaryTextColor: Int? = null
    private var iconsColor: Int? = null

    fun setCategoryTitleColor(color: Int) {
        this.categoryTitleColor = color
    }

    fun getCategoryTitleColor(): Int {
        return categoryTitleColor ?: getThemeColor(R.attr.colorAccent)
    }

    fun setAccentColor(color: Int) {
        this.accentColor = color
    }

    fun getAccentColor(): Int {
        return accentColor ?: getThemeColor(R.attr.colorAccent)
    }

    fun setPrimaryTextColor(color: Int) {
        this.primaryTextColor = color
    }

    fun getPrimaryTextColor(): Int {
        return primaryTextColor ?: getThemeColor(android.R.attr.textColorPrimary)
    }

    fun setSecondaryTextColor(color: Int) {
        this.secondaryTextColor = color
    }

    fun getSecondaryTextColor(): Int {
        return secondaryTextColor ?: getThemeColor(android.R.attr.textColorSecondary)
    }

    fun setIconsColor(color: Int) {
        this.iconsColor = color
    }

    fun getIconsColor(): Int {
        return iconsColor ?: context.color(R.color.icons_color)
    }

    private fun getThemeColor(attr: Int): Int {
        val typedValue = TypedValue()
        val theme: Resources.Theme = context.theme
        theme.resolveAttribute(attr, typedValue, true)
        val arr: TypedArray = context.obtainStyledAttributes(
            typedValue.data, intArrayOf(attr)
        )
        val color = arr.getColor(0, -1)
        arr.recycle()
        return color
    }

}
