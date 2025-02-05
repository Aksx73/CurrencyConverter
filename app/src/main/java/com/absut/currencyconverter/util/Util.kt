package com.absut.currencyconverter.util

import android.os.Build
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

}