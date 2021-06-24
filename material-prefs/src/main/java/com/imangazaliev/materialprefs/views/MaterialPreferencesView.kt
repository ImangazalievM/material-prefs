package com.imangazaliev.materialprefs.views

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView

class MaterialPreferencesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    themeResId: Int = 0
) : ScrollView(context, attrs, themeResId) {

    private val container = LinearLayout(context)

    init {
        container.orientation = LinearLayout.VERTICAL
        container.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        container.orientation = LinearLayout.VERTICAL
        addView(container)
    }

    fun addCategoryView(categoryView: PreferenceCategoryView) {
        container.addView(categoryView)
    }

}