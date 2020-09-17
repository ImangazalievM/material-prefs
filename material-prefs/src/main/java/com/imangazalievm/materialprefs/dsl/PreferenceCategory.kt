package com.imangazalievm.materialprefs.dsl

import com.imangazalievm.materialprefs.dsl.preferences.*
import com.imangazalievm.materialprefs.util.checkIsTypeSupported
import com.imangazalievm.materialprefs.views.PreferenceCategoryView
import kotlin.reflect.KClass

@PreferencesMarker
class PreferenceCategory(
    val container: PrefsContainer,
    val appearanceManager: PreferencesAppearance,
    private val title: String
) {

    private val preferences = mutableListOf<GenericPreference>()

    fun preference(builder: PreferenceBuilder<SimplePreference>) {
        SimplePreference(container, appearanceManager)
            .apply(builder)
            .also { addPreference(it) }
    }

    fun checkbox(key: String, builder: PreferenceBuilder<CheckboxPreference>) {
        CheckboxPreference(key, container, appearanceManager)
            .apply(builder)
            .also { addPreference(it) }
    }

    fun switch(key: String, builder: PreferenceBuilder<SwitchPreference>) {
        SwitchPreference(key, container, appearanceManager)
            .apply(builder)
            .also { addPreference(it) }
    }

    fun <T : Any> labelPreference(
        key: String,
        valueType: KClass<T>,
        builder: PreferenceBuilder<LabelPreference<T>>
    ) {
        checkIsTypeSupported(valueType)
        LabelPreference(key, valueType, container, appearanceManager)
            .apply(builder)
            .also { addPreference(it) }
    }

    fun addPreference(preference: GenericPreference) {
        preferences.add(preference)
    }

    internal fun getView(): PreferenceCategoryView {
        val view = PreferenceCategoryView(container.context)
        view.setTitle(title)
        view.setTitleColor(appearanceManager.getCategoryTitleColor())

        preferences.forEach {
            view.addPreferenceView(it.view)
        }
        return view
    }

}
