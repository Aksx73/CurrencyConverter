package com.absut.currencyconverter.util

import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.OffsetMapping
import java.text.DecimalFormat

class ThousandsSeparatorVisualTransformation : VisualTransformation {

	private val formatter = DecimalFormat("#,###.####").apply {
		isDecimalSeparatorAlwaysShown = false
		minimumFractionDigits = 0
		maximumFractionDigits = 4
		decimalFormatSymbols = decimalFormatSymbols.apply {
			groupingSeparator = ','
			decimalSeparator = '.'
		}
	}

	override fun filter(text: AnnotatedString): TransformedText {
		// Handle empty text
		if (text.isEmpty()) return TransformedText(text, OffsetMapping.Identity)

		// Split into integer and decimal parts if decimal exists
		val hasDot = text.text.contains('.')
		val formattedText = if (hasDot) {
			val parts = text.text.split('.')
			val integerPart = parts[0].ifEmpty { "0" }
			val formattedInteger = try {
				formatter.format(integerPart.toLongOrNull() ?: 0)
			} catch (e: Exception) {
				integerPart
			}

			// Keep original decimal part
			val decimalPart = parts.getOrNull(1) ?: ""
			if (decimalPart.isEmpty()) "$formattedInteger." else "$formattedInteger.$decimalPart"
		} else {
			// Format without decimal
			try {
				formatter.format(text.text.toLongOrNull() ?: 0)
			} catch (e: Exception) {
				text.text
			}
		}

		// Create offset mapping to handle cursor position with commas
		val offsetMapping = object : OffsetMapping {
			override fun originalToTransformed(offset: Int): Int {
				var extraOffsetForOnlyDecimal = false
				// Count commas before cursor in transformed text
				val rawBeforeCursor = text.text.take(offset)
				val commasBeforeCursor = if (hasDot && rawBeforeCursor.contains('.')) {
					val parts = rawBeforeCursor.split('.')
					val intPart = parts[0].ifEmpty { "0" }
					extraOffsetForOnlyDecimal = parts[0].isEmpty()
					countCommasInFormattedNumber(intPart.toLongOrNull() ?: 0)
				} else {
					countCommasInFormattedNumber(rawBeforeCursor.toLongOrNull() ?: 0)
				}
				return if (extraOffsetForOnlyDecimal) offset + commasBeforeCursor + 1 else offset + commasBeforeCursor
			}

			override fun transformedToOriginal(offset: Int): Int {
				// Ensure we don't go out of bounds
				if (offset >= formattedText.length) return text.length

				// Count commas before this position in transformed text
				val commaCount = formattedText.substring(0, offset).count { it == ',' }
				return offset - commaCount
			}

			// Helper to count commas in formatted number
			private fun countCommasInFormattedNumber(number: Long): Int {
				val formatted = formatter.format(number)
				return formatted.count { it == ',' }
			}
		}

		return TransformedText(AnnotatedString(formattedText), offsetMapping)
	}
}