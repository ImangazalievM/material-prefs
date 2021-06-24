package com.imangazaliev.materialprefs.dsl.preferences

import com.imangazaliev.materialprefs.dsl.PreferencesAppearance
import com.imangazaliev.materialprefs.dsl.PreferencesMarker
import com.imangazaliev.materialprefs.dsl.PrefsContainer
import com.imangazaliev.materialprefs.util.getValueWithType
import com.imangazaliev.materialprefs.views.simple.LabelPreferenceView
import kotlinx.android.synthetic.main.pref_label.view.*
import kotlin.reflect.KClass

@PreferencesMarker
class LabelPreference<T : Any>(
    key: String,
    private val valueType: KClass<T>,
    container: PrefsContainer,
    appearanceManager: PreferencesAppearance
) : BasePreference<LabelPreference<T>, LabelPreferenceView, Unit>(
    key = key,
    container = container,
    appearanceManager = appearanceManager
) {

    private var valueConverter: ((T) -> String)? = null
    private var onClickListener: (() -> Unit)? = null

    fun valuePresenter(converter: (T) -> String) {
        this.valueConverter = converter
    }

    fun onClick(listener: () -> Unit) {
        this.onClickListener = listener
    }

    override fun createView(): LabelPreferenceView {
        return LabelPreferenceView(context)
    }

    override fun loadValue(view: LabelPreferenceView) {
        val value = storage.getValueWithType(key, valueType)
        view.valueText.text = valueConverter?.invoke(value) ?: value.toString()
    }

    override fun initView(view: LabelPreferenceView) {
        super.initView(view)

        view.setOnClickListener {
            onClickListener?.invoke()
        }
    }

}