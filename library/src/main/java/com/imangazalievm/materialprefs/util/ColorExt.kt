package com.imangazalievm.materialprefs.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.core.graphics.drawable.DrawableCompat
import com.imangazalievm.materialprefs.R
import kotlin.math.roundToInt

internal fun Drawable.modifySwitchDrawable(
    context: Context,
    @ColorInt requestedTint: Int,
    thumb: Boolean,
    compatSwitch: Boolean,
    useDarker: Boolean
): Drawable? {
    val sl = checkableColorStateList(
        context = context,
        requestedTint = requestedTint,
        thumb = thumb,
        compatSwitch = compatSwitch,
        useDarker = useDarker
    )
    return this.tint(sl)
}

internal fun checkableColorStateList(
    context: Context,
    @ColorInt requestedTint: Int,
    thumb: Boolean,
    compatSwitch: Boolean,
    useDarker: Boolean
): ColorStateList {
    var tint = requestedTint
    if (useDarker) {
        tint = tint.shiftColor(1.1f)
    }
    tint = tint.adjustAlpha(if (compatSwitch && !thumb) 0.5f else 1.0f)

    val disabled: Int
    var normal: Int
    if (thumb) {
        disabled = context.color(
            if (useDarker) R.color.mpf_switch_thumb_disabled_dark
            else R.color.mpf_switch_thumb_disabled_light
        )
        normal = context.color(
            if (useDarker) R.color.mpf_switch_thumb_normal_dark
            else R.color.mpf_switch_thumb_normal_light
        )
    } else {
        disabled = context.color(
            if (useDarker) R.color.mpf_switch_track_disabled_dark
            else R.color.mpf_switch_track_disabled_light
        )
        normal = context.color(
            if (useDarker) R.color.mpf_switch_track_normal_dark
            else R.color.mpf_switch_track_normal_light
        )
    }

    // Stock switch includes its own alpha
    if (!compatSwitch) {
        normal = normal.stripAlpha()
    }

    return ColorStateList(
        arrayOf(
            intArrayOf(-android.R.attr.state_enabled),
            intArrayOf(
                android.R.attr.state_enabled,
                -android.R.attr.state_activated,
                -android.R.attr.state_checked
            ),
            intArrayOf(
                android.R.attr.state_enabled,
                android.R.attr.state_activated
            ),
            intArrayOf(
                android.R.attr.state_enabled,
                android.R.attr.state_checked
            )
        ),
        intArrayOf(disabled, normal, tint, tint)
    )
}

@ColorInt
internal fun Int.stripAlpha(): Int {
    return Color.rgb(
        Color.red(this),
        Color.green(this),
        Color.blue(this)
    )
}

@CheckResult
internal fun Drawable?.tint(@ColorInt color: Int): Drawable? {
    var result: Drawable = this ?: return null
    result = DrawableCompat.wrap(result.mutate())
    DrawableCompat.setTintMode(result, PorterDuff.Mode.SRC_IN)
    DrawableCompat.setTint(result, color)
    return result
}

@CheckResult
internal fun Drawable?.tint(sl: ColorStateList): Drawable? {
    var result: Drawable = this ?: return null
    result = DrawableCompat.wrap(result.mutate())
    DrawableCompat.setTintList(result, sl)
    return result
}

@ColorInt
internal fun Int.adjustAlpha(factor: Float): Int {
    val alpha = (Color.alpha(this) * factor).roundToInt()
    val red = Color.red(this)
    val green = Color.green(this)
    val blue = Color.blue(this)
    return Color.argb(alpha, red, green, blue)
}

@ColorInt
internal fun Int.shiftColor(
    @FloatRange(from = 0.0, to = 2.0) by: Float
): Int {
    if (by == 1f) return this
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    hsv[2] *= by // value component
    return Color.HSVToColor(hsv)
}