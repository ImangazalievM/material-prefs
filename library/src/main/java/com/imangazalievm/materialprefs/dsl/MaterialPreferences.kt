package com.imangazalievm.materialprefs.dsl

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.imangazalievm.materialprefs.dsl.preferences.Preference

typealias PreferencesBuilder = MaterialPreferences.() -> Unit
typealias GenericFormField = Preference<*, *, *>

@DslMarker
annotation class PreferencesMarker

@PreferencesMarker
class MaterialPreferences constructor(
    private val container: PrefsContainer
) {

    private val context = container.context
    private val appearanceManager = PreferencesAppearance(container.context)
    private val categories = mutableListOf<PreferenceCategory>()

    fun categoryTitleColor(@ColorInt color: Int) {
        appearanceManager.setCategoryTitleColor(color)
    }

    fun categoryTitleColorRes(@ColorRes colorResId: Int) {
        val color = ContextCompat.getColor(context, colorResId)
        appearanceManager.setCategoryTitleColor(color)
    }

    fun accentColor(@ColorInt color: Int) {
        appearanceManager.setAccentColor(color)
    }

    fun accentColorRes(@ColorRes colorResId: Int) {
        val color = ContextCompat.getColor(context, colorResId)
        appearanceManager.setCategoryTitleColor(color)
    }

    fun primaryTextColor(@ColorInt color: Int) {
        appearanceManager.setPrimaryTextColor(color)
    }

    fun primaryTextColorRes(@ColorRes colorResId: Int) {
        val color = ContextCompat.getColor(context, colorResId)
        appearanceManager.setPrimaryTextColor(color)
    }

    fun secondaryTextColor(@ColorInt color: Int) {
        appearanceManager.setSecondaryTextColor(color)
    }

    fun secondaryTextColorRes(@ColorRes colorResId: Int) {
        val color = ContextCompat.getColor(context, colorResId)
        appearanceManager.setSecondaryTextColor(color)
    }

    fun category(
        @StringRes titleResId: Int,
        builder: PreferenceCategory.() -> Unit
    ) = category(context.getString(titleResId), builder)

    fun category(
        title: String = "",
        builder: PreferenceCategory.() -> Unit
    ) {
        PreferenceCategory(container, appearanceManager, title)
            .apply(builder)
            .also { categories.add(it) }

    }

    internal fun init() {
        categories.forEach {
            val categoryView = it.getView()
            container.addCategoryView(categoryView)
        }
    }

}
