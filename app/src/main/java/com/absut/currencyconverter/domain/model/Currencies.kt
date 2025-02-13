package com.absut.currencyconverter.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


//@TypeConverters(CurrencyListConverter::class)
@Keep
@Serializable
data class Currencies(
	val currency: List<Currency>
)

@Keep
@Serializable
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
