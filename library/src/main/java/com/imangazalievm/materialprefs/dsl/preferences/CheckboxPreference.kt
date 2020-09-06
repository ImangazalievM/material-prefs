package com.imangazalievm.materialprefs.dsl.preferences

import com.imangazalievm.materialprefs.dsl.PreferencesAppearance
import com.imangazalievm.materialprefs.dsl.PrefsContainer
import com.imangazalievm.materialprefs.views.checkable.CheckboxPreferenceView

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