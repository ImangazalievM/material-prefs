package com.imangazalievm.materialprefs.dsl

import android.content.Context
import com.imangazalievm.materialprefs.storage.PreferencesStorage
import com.imangazalievm.materialprefs.views.MaterialPreferencesView
import com.imangazalievm.materialprefs.views.PreferenceCategoryView

class PrefsContainer(
    private val viewsContainer: MaterialPreferencesView,
    val storage: PreferencesStorage
) {

    val context: Context = viewsContainer.context

    internal fun addCategoryView(categoryView: PreferenceCategoryView) {
        viewsContainer.addCategoryView(categoryView)
    }


}