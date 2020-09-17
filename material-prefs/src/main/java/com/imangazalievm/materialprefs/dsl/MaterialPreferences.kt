package com.imangazalievm.materialprefs.dsl

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.imangazalievm.materialprefs.dsl.preferences.Preference

typealias PreferencesBuilder = MaterialPreferences.() -> Unit
typealias GenericPreference = Preference<*, *, *>

@DslMarker
annotation class PreferencesMarker

@PreferencesMarker
class MaterialPreferences constructor(
    private val container: PrefsContainer
) {

    private val context = container.context
    private val appearanceManager = PreferencesAppearance(container.context)
    private val categories = mutableListOf<PreferenceCategory>()

    fun appearance(
        builder: PreferencesAppearance.Configurator.() -> Unit
    ) {
        builder(PreferencesAppearance.Configurator(context, appearanceManager))
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
