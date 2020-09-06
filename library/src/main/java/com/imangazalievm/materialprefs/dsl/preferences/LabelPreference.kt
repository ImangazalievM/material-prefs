package com.imangazalievm.materialprefs.dsl.preferences

import com.imangazalievm.materialprefs.dsl.PreferencesAppearance
import com.imangazalievm.materialprefs.dsl.PreferencesMarker
import com.imangazalievm.materialprefs.dsl.PrefsContainer
import com.imangazalievm.materialprefs.views.simple.LabelPreferenceView

@PreferencesMarker
class LabelPreference(
    key: String,
    container: PrefsContainer,
    appearanceManager: PreferencesAppearance
) : BasePreference<LabelPreference, LabelPreferenceView, Unit>(
    key = key,
    container = container,
    appearanceManager = appearanceManager
) {

    private var onClickListener: (() -> Unit)? = null

    fun onClick(listener: () -> Unit) {
        this.onClickListener = listener
    }

    override fun createView(): LabelPreferenceView {
        return LabelPreferenceView(context)
    }

    override fun loadValue(view: LabelPreferenceView) {

    }

    override fun initView(view: LabelPreferenceView) {
        super.initView(view)

        view.setOnClickListener {
            onClickListener?.invoke()
        }
    }

}