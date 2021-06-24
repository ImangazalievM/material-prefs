package com.imangazaliev.materialprefs.dsl

import android.content.Context
import com.imangazaliev.materialprefs.storage.PreferencesStorage
import com.imangazaliev.materialprefs.views.MaterialPreferencesView
import com.imangazaliev.materialprefs.views.PreferenceCategoryView

class PrefsContainer(
    private val viewsContainer: MaterialPreferencesView,
    val storage: PreferencesStorage
) {

    val context: Context = viewsContainer.context

    internal fun addCategoryView(categoryView: PreferenceCategoryView) {
        viewsContainer.addCategoryView(categoryView)
    }


}