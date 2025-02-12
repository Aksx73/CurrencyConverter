package com.absut.currencyconverter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


//@TypeConverters(CurrencyListConverter::class)
data class Currencies(
	val currency: List<Currency>
)

@Entity(tableName = "currency")
data class Currency(
	@PrimaryKey val code: String,
	val name: String?
)

/*
class CurrencyListConverter {
	@TypeConverter
	fun fromCurrencyList(currency: List<Currency>): String {
		return Json.encodeToString(currency)
	}

	@TypeConverter
	fun toCurrencyList(currency: String): List<Currency> {
		return Json.decodeFromString(currency)
	}
}*/
