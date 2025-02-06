package com.absut.currencyconverter.data.remote

import com.absut.currencyconverter.data.remote.dto.CurrenciesDto
import com.absut.currencyconverter.data.remote.dto.RatesEurDto
import com.bjsindia.bjscommunity.data.remote.NetworkResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val client: HttpClient) {

	suspend fun getCurrencies(endpoint: String = ApiRoutes.GET_CURRENCIES): NetworkResult<CurrenciesDto> {
		return try {
			val url = "$endpoint.json"
			val response = client.get(url)
			NetworkResult.Success(response.body())
		} catch (e: Exception) {
			NetworkResult.Error(e.message ?: "An unknown error occurred")
		}
	}

	suspend fun getRatesForUSD(
		endpoint: String = ApiRoutes.GET_RATES,
		baseCurrency: String = "eur"
	): NetworkResult<RatesEurDto> {
		return try {
			val url = String.format(endpoint, baseCurrency) + ".json"
			val response = client.get(url)
			NetworkResult.Success(response.body())
		} catch (e: Exception) {
			NetworkResult.Error(e.message ?: "An unknown error occurred")
		}
	}


}