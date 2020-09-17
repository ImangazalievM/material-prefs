package com.imangazalievm.materialprefs.dsl.preferences

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import com.imangazalievm.materialprefs.dsl.PreferencesAppearance
import com.imangazalievm.materialprefs.dsl.PreferencesMarker
import com.imangazalievm.materialprefs.dsl.PrefsContainer
import com.imangazalievm.materialprefs.views.BasePreferenceView
import java.lang.IllegalStateException

@PreferencesMarker
abstract class BasePreference<P, V : BasePreferenceView, T : Any>(
    private val appearanceManager: PreferencesAppearance,
    container: PrefsContainer,
    key: String
) : Preference<P, V, T>(container, key) where P : BasePreference<P, V, T> {

    protected var title: String? = null
    protected var summary: String? = null
    protected var icon: Drawable? = null

    fun title(@StringRes titleResId: Int) {
        title(context.getString(titleResId))
    }

    fun title(title: String) {
        this.title = title
    }

    fun summary(@StringRes summaryResId: Int) {
        summary(context.getString(summaryResId))
    }

    fun summary(summary: String) {
        this.summary = summary
    }

    fun icon(@DrawableRes iconResId: Int) {
        val icon = ResourcesCompat.getDrawable(context.resources, iconResId, context.theme)!!
        icon(icon)
    }

    fun icon(icon: Drawable) {
        this.icon = icon
    }

    override fun initView(view: V) {
        if (title == null) {
            throw IllegalStateException("The title is not set")
        }

        view.setTitleColor(appearanceManager.getPrimaryTextColor())
        view.setSummaryColor(appearanceManager.getSecondaryTextColor())
        view.setIconColor(appearanceManager.getIconsColor())
        view.setTitle(title)
        view.setSummary(summary)
        view.setIcon(icon)
    }

}