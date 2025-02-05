package com.absut.currencyconverter.data.remote

object ApiRoutes {
	const val BASE_URL = "https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1"
	const val GET_CURRENCIES = "/currencies"
	const val GET_RATES = "/currencies/%1\$s" //{currencyCode}
}