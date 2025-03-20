package com.absut.currencyconverter.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/*@Keep
@Serializable
data class Rates(
	val date: Long,
	val baseCurrencyCode: String,
	val rates: List<CurrencyRate>
)*/

@Keep
@Serializable
data class Rates(
	val rate: List<CurrencyRate>,
)

@Keep
@Serializable
@Entity(tableName = "eu_rates")
data class CurrencyRate(
	val date: Long,
	@PrimaryKey val code: String,
	val value: Double,
	val baseCurrencyCode: String,
)

/*
class CurrencyRateConverter {
	@TypeConverter
	fun fromCurrencyRate(currencyRate: List<CurrencyRate>): String {
		return Json.encodeToString(currencyRate)
	}

	@TypeConverter
	fun toCurrencyRate(currencyRate: String): List<CurrencyRate> {
		return Json.decodeFromString(currencyRate)
	}
}*/
