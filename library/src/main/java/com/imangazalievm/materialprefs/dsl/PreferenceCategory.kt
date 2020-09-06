package com.imangazalievm.materialprefs.dsl

import com.imangazalievm.materialprefs.dsl.preferences.*
import com.imangazalievm.materialprefs.views.PreferenceCategoryView

@PreferencesMarker
class PreferenceCategory(
    private val container: PrefsContainer,
    private val appearanceManager: PreferencesAppearance,
    private val title: String
) {

    private val preferences = mutableListOf<GenericFormField>()

    fun preference(builder: PreferenceBuilder<SimplePreference>) {
        SimplePreference(container, appearanceManager)
            .apply(builder)
            .also { preferences.add(it) }
    }

    fun checkbox(key: String, builder: PreferenceBuilder<CheckboxPreference>) {
        CheckboxPreference(key, container, appearanceManager)
            .apply(builder)
            .also { preferences.add(it) }
    }

    fun switch(key: String, builder: PreferenceBuilder<SwitchPreference>) {
        SwitchPreference(key, container, appearanceManager)
            .apply(builder)
            .also { preferences.add(it) }
    }

    fun labelPreference(key: String, builder: PreferenceBuilder<LabelPreference>) {
        LabelPreference(key, container, appearanceManager)
            .apply(builder)
            .also { preferences.add(it) }
    }

    fun listSingleChoice(
        key: String,
        showRadioButtons: Boolean = true,
        builder: PreferenceBuilder<LabelPreference>
    ) {
        LabelPreference(key, container, appearanceManager)
            .apply(builder)
            .also { preferences.add(it) }
    }

    fun listMultiChoice(key: String, builder: PreferenceBuilder<LabelPreference>) {
        LabelPreference(key, container, appearanceManager)
            .apply(builder)
            .also { preferences.add(it) }
    }

    fun textInput(key: String, builder: PreferenceBuilder<LabelPreference>) {
        LabelPreference(key, container, appearanceManager)
            .apply(builder)
            .also { preferences.add(it) }
    }

    fun dateTime(key: String, builder: PreferenceBuilder<LabelPreference>) {
        LabelPreference(key, container, appearanceManager)
            .apply(builder)
            .also { preferences.add(it) }
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
