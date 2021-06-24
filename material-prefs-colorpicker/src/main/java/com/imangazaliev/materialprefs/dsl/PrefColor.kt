package com.imangazaliev.materialprefs.dsl

class PrefColor(
    private val color: Int
) {

    fun asIntColor(): Int = color

    fun asHexColor() : String = java.lang.String.format("#%06X", 0xFFFFFF and color)

}