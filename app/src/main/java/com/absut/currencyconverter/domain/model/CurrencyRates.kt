package com.absut.currencyconverter.domain.model

data class Rates(
	val date: Long,
	val baseCurrencyCode: String,
	val rates : List<CurrencyRate>
)

data class CurrencyRate(
	val code: String,
	val value : Double,
	val baseCurrencyCode : String,
)