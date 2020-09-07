package com.imangazalievm.materialprefs.dsl.preferences

import com.imangazalievm.materialprefs.dsl.PreferencesAppearance
import com.imangazalievm.materialprefs.dsl.PreferencesMarker
import com.imangazalievm.materialprefs.dsl.PrefsContainer
import com.imangazalievm.materialprefs.views.simple.LabelPreferenceView
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
        val value = when (valueType) {
            String::class -> storage.getString(key) as T
            Int::class -> storage.getInt(key) as T
            Long::class -> storage.getLong(key) as T
            Float::class -> storage.getFloat(key) as T
            Boolean::class -> storage.getBoolean(key) as T
            else -> throw IllegalStateException()
        }
        view.valueText.text = valueConverter?.invoke(value) ?: value.toString()
    }

    override fun initView(view: LabelPreferenceView) {
        super.initView(view)

        view.setOnClickListener {
            onClickListener?.invoke()
        }
    }

    companion object {

        private val supportedTypes = listOf(
            String::class,
            Int::class,
            Long::class,
            Float::class,
            Boolean::class
        )

        fun isSupportedType(type: KClass<*>): Boolean {
            return supportedTypes.contains(type)
        }
    }

}