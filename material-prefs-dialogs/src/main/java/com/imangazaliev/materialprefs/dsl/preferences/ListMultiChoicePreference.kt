package com.imangazaliev.materialprefs.dsl.preferences

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsMultiChoice
import com.imangazaliev.materialprefs.dsl.ListItem
import com.imangazaliev.materialprefs.dsl.PreferencesAppearance
import com.imangazaliev.materialprefs.dsl.PreferencesMarker
import com.imangazaliev.materialprefs.dsl.PrefsContainer
import com.imangazaliev.materialprefs.views.simple.LabelPreferenceView

@PreferencesMarker
class ListMultiChoicePreference<T : Any>(
    key: String,
    container: PrefsContainer,
    appearanceManager: PreferencesAppearance
) : BasePreference<ListMultiChoicePreference<T>, LabelPreferenceView, String>(
    key = key,
    container = container,
    appearanceManager = appearanceManager
) {

    private var dialogTitle: String? = null
    private var allowEmptySelection: Boolean = true
    private lateinit var items: List<ListItem<T>>
    private lateinit var listValuesSerializer: (List<T>) -> String
    private lateinit var listValuesDeserializer: (String) -> List<T>
    private var onItemsSelected: ((List<ListItem<T>>) -> Unit)? = null
    private var saveButtonLabel: String? = null

    fun items(items: List<ListItem<T>>) {
        this.items = items
    }

    fun dialogTitle(dialogTitle: String) {
        this.dialogTitle = dialogTitle
    }

    fun allowEmptySelection(allowEmptySelection: Boolean) {
        this.allowEmptySelection = allowEmptySelection
    }

    fun listValuesSerializer(serializer: (List<T>) -> String) {
        this.listValuesSerializer = serializer
    }

    fun listValuesDeserializer(deserializer: (String) -> List<T>) {
        this.listValuesDeserializer = deserializer
    }

    fun onItemsSelected(onItemsSelected: (List<ListItem<T>>) -> Unit) {
        this.onItemsSelected = onItemsSelected
    }

    fun buttonLabel(buttonLabel: String) {
        this.saveButtonLabel = buttonLabel
    }

    override fun createView(): LabelPreferenceView {
        return LabelPreferenceView(context)
    }

    override fun loadValue(view: LabelPreferenceView) {

    }

    override fun initView(view: LabelPreferenceView) {
        super.initView(view)

        view.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val initialValues = getInitialValues()
        val allValues = items.map { it.value }
        val indices = initialValues
            .map { allValues.indexOf(it) }
            .filter { it != -1 }
            .toIntArray()

        MaterialDialog(context).show {
            title(text = dialogTitle ?: title)
            val titles = items.map { it.title }
            listItemsMultiChoice(
                items = titles,
                initialSelection = indices,
                allowEmptySelection = allowEmptySelection
            ) { _, indicies, _ ->
                val selectedItems = indicies.map { items[it] }
                val values = selectedItems.map { it.value }
                saveValue(listValuesSerializer(values))
                onItemsSelected?.invoke(selectedItems)
            }
            positiveButton(text = saveButtonLabel)
        }
    }

    private fun getInitialValues(): List<T> {
        val serializedValues = valueString()
        return serializedValues?.let {
            listValuesDeserializer(it)
        } ?: emptyList()
    }

}