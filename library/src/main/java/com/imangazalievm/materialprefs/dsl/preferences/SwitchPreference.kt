package com.imangazalievm.materialprefs.dsl.preferences

import com.imangazalievm.materialprefs.dsl.PreferencesAppearance
import com.imangazalievm.materialprefs.dsl.PrefsContainer
import com.imangazalievm.materialprefs.views.checkable.SwitchPreferenceView

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

    override fun loadValue(view: SwitchPreferenceView) {

    }

}