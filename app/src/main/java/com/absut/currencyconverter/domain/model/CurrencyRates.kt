package com.absut.currencyconverter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(tableName = "eu_rates")
@TypeConverters(CurrencyRateConverter::class)
data class Rates(
	@PrimaryKey val date: Long,
	val baseCurrencyCode: String,
	val rates: List<CurrencyRate>
)

data class CurrencyRate(
	val code: String,
	val value: Double,
	val baseCurrencyCode: String,
)

class CurrencyRateConverter {
	@TypeConverter
	fun fromCurrencyRate(currencyRate: List<CurrencyRate>): String {
		return Json.encodeToString(currencyRate)
	}

	@TypeConverter
	fun toCurrencyRate(currencyRate: String): List<CurrencyRate> {
		return Json.decodeFromString(currencyRate)
	}
}