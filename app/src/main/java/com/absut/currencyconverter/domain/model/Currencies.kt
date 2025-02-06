package com.absut.currencyconverter.domain.model

data class Currencies(
	val currency : List<Currency>
)

data class Currency(
	val code: String,
	val name: String?
)
