package com.imangazaliev.materialprefs.dsl.preferences

import com.imangazaliev.materialprefs.dsl.PreferencesAppearance
import com.imangazaliev.materialprefs.dsl.PrefsContainer
import com.imangazaliev.materialprefs.views.checkable.SwitchPreferenceView

class SwitchPreference(
    key: String,
    container: PrefsContainer,
    appearanceManager: PreferencesAppearance
) : CheckablePreference<SwitchPreference, SwitchPreferenceView>(
    container = container,
    key = key,
    appearanceManager = appearanceManager
) {

    override fun createView(): SwitchPreferenceView {
        return SwitchPreferenceView(context)
    }

}