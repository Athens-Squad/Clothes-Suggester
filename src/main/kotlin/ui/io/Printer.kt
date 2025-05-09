package org.example.ui.io

import org.example.ui.utils.Colors
import org.example.ui.utils.TextStyle


class Printer(
    private val colors: Colors
) {
	fun printText(
		text: String,
		textStyle : TextStyle = TextStyle.NORMAL,
		withNewLine: Boolean = true
	) {
		val coloredText = textStyle.format(text)
		if (withNewLine) {
			println(coloredText)
		} else {
			print(coloredText)
		}
	}
}