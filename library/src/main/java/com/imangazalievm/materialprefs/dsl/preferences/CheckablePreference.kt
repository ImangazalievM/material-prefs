package com.imangazalievm.materialprefs.dsl.preferences

import com.imangazalievm.materialprefs.dsl.PreferencesAppearance
import com.imangazalievm.materialprefs.dsl.PrefsContainer
import com.imangazalievm.materialprefs.views.checkable.CheckablePreferenceView

abstract class CheckablePreference<P, V : CheckablePreferenceView>(
    key: String,
    container: PrefsContainer,
    private val appearanceManager: PreferencesAppearance
) : BasePreference<P, V, Boolean>(
    container = container,
    key = key,
    appearanceManager = appearanceManager
) where P : CheckablePreference<P, V> {

    private var onCheckedListener: ((isChecked: Boolean) -> Unit)? = null

    fun onChecked(listener: (isChecked: Boolean) -> Unit) {
        this.onCheckedListener = listener
    }

    override fun initView(view: V) {
        super.initView(view)

        view.setColor(appearanceManager.getAccentColor())
        view.setOnClickListener {
            val newValue = !view.checkableView.isChecked
            view.checkableView.isChecked = newValue
            onCheckedListener?.invoke(newValue)
        }
    }

    override fun loadValue(view: V) {
        view.setChecked(valueBoolean())
    }

}