package com.imangazaliev.materialprefs.dsl.preferences

import com.imangazaliev.materialprefs.dsl.PreferencesAppearance
import com.imangazaliev.materialprefs.dsl.PrefsContainer
import com.imangazaliev.materialprefs.views.checkable.CheckboxPreferenceView

class CheckboxPreference(
    key: String,
    container: PrefsContainer,
    appearanceManager: PreferencesAppearance
) : CheckablePreference<CheckboxPreference, CheckboxPreferenceView>(
    container = container,
    key = key,
    appearanceManager = appearanceManager
) {

    override fun createView(): CheckboxPreferenceView {
        return CheckboxPreferenceView(context)
    }

}