package com.absut.currencyconverter.util

import android.os.Build
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Locale

object Util {

	fun getTimeMillisFromDate(dateString: String): Long {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			val localDate = LocalDate.parse(dateString)
			val zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault())
			return zonedDateTime.toInstant().toEpochMilli()
		} else {
			val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
			val date = dateFormat.parse(dateString)
			return date?.time ?: 0L
		}
	}

	fun getCurrencySymbol(currencyCode: String): String {
		return when (currencyCode) {
			"usd" -> "$"
			"eur" -> "€"
			else -> ""
		}
	}

	// Helper function to format the number with commas as thousands separator
	fun formatCurrency(value: String): String {
		val decimalFormat = DecimalFormat("#,##0.####", DecimalFormatSymbols().apply {
			groupingSeparator = ','
			decimalSeparator = '.'
		})

		return try {
			val doubleValue = value.toDoubleOrNull()
			if (doubleValue != null) {
				decimalFormat.format(doubleValue)
			} else {
				value
			}
		} catch (e: Exception) {
			value
		}
	}


}