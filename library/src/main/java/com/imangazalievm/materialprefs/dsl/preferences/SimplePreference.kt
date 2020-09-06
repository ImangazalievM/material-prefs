package com.imangazalievm.materialprefs.dsl.preferences

import com.imangazalievm.materialprefs.dsl.PreferencesAppearance
import com.imangazalievm.materialprefs.dsl.PreferencesMarker
import com.imangazalievm.materialprefs.dsl.PrefsContainer
import com.imangazalievm.materialprefs.views.simple.SimplePreferenceView

@PreferencesMarker
class SimplePreference(
    container: PrefsContainer,
    private val appearanceManager: PreferencesAppearance
) : BasePreference<SimplePreference, SimplePreferenceView, Unit>(
    container = container,
    key = "",
    appearanceManager = appearanceManager
) {

    private var onClickListener: (() -> Unit)? = null
    private var showArrow: Boolean = false

    fun showArrow(showArrow: Boolean) {
        this.showArrow = showArrow
    }

    fun onClick(listener: () -> Unit) {
        this.onClickListener = listener
    }

    override fun createView(): SimplePreferenceView {
        return SimplePreferenceView(context)
    }

    override fun initView(view: SimplePreferenceView) {
        super.initView(view)

        view.showArrow(showArrow)
        view.setOnClickListener { onClickListener?.invoke() }
    }

    override fun loadValue(view: SimplePreferenceView) {
        // hasn't value
    }

}