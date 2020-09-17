package com.imangazalievm.materialprefs.dsl.preferences

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.imangazalievm.materialprefs.dsl.ListItem
import com.imangazalievm.materialprefs.dsl.PreferencesAppearance
import com.imangazalievm.materialprefs.dsl.PreferencesMarker
import com.imangazalievm.materialprefs.dsl.PrefsContainer
import com.imangazalievm.materialprefs.util.getValueWithType
import com.imangazalievm.materialprefs.util.putValueWithType
import com.imangazalievm.materialprefs.views.simple.LabelPreferenceView
import kotlin.reflect.KClass

@PreferencesMarker
class ListSinglePreference<T : Any>(
    key: String,
    private val valueType: KClass<T>,
    container: PrefsContainer,
    appearanceManager: PreferencesAppearance
) : BasePreference<ListSinglePreference<T>, LabelPreferenceView, Unit>(
    key = key,
    container = container,
    appearanceManager = appearanceManager
) {

    private var dialogTitle: String? = null
    private lateinit var items: List<ListItem<T>>
    private var onSelectedListener: ((ListItem<T>) -> Unit)? = null
    private var showRadioButtons: Boolean = false
    private var saveButtonLabel: String? = null

    fun items(items: List<ListItem<T>>) {
        this.items = items
    }

    fun dialogTitle(dialogTitle: String) {
        this.dialogTitle = dialogTitle
    }

    fun onItemSelected(listener: (ListItem<T>) -> Unit) {
        this.onSelectedListener = listener
    }

    fun showRadioButtons(showRadioButtons: Boolean, buttonLabel: String? = null) {
        this.showRadioButtons = showRadioButtons
        this.saveButtonLabel = buttonLabel
    }

    override fun createView(): LabelPreferenceView {
        return LabelPreferenceView(context)
    }

    override fun loadValue(view: LabelPreferenceView) {
        val value = storage.getValueWithType(key, valueType)
        val currentItem = items.firstOrNull {
            it.value == value
        }
        view.setLabelText(currentItem?.title)
    }

    override fun initView(view: LabelPreferenceView) {
        super.initView(view)

        view.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val titles = items.map { it.title }

        MaterialDialog(context).show {
            title(text = dialogTitle ?: title)

            val currentValue = storage.getValueWithType(key, valueType)
            val selectedItem = items.indexOfFirst {
                it.value == currentValue
            }
            if (showRadioButtons) {
                listItemsSingleChoice(
                    items = titles,
                    initialSelection = selectedItem
                ) { _, index, _ ->
                    onItemSelected(items[index])
                }
            } else {
                listItems(items = titles) { _, index, _ ->
                    onItemSelected(items[index])
                }
            }

            saveButtonLabel?.let {
                positiveButton(text = it)
            }
        }
    }

    private fun onItemSelected(selectedItem: ListItem<T>) {
        storage.putValueWithType(key, selectedItem.value)
        view.setLabelText(selectedItem.title)
        onSelectedListener?.invoke(selectedItem)
    }

}