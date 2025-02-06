package com.absut.currencyconverter.data.remote

import com.absut.currencyconverter.data.remote.dto.CurrenciesDto
import com.absut.currencyconverter.data.remote.dto.RatesEurDto
import com.absut.currencyconverter.util.Constants
import com.bjsindia.bjscommunity.data.remote.NetworkResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val client: HttpClient) {

	/*suspend fun getCurrencies(endpoint: String = ApiRoutes.GET_CURRENCIES): NetworkResult<CurrenciesDto> {
		return try {
			val url = "$endpoint.json"
			val response = client.get(url)
			NetworkResult.Success(response.body())
		} catch (e: Exception) {
			NetworkResult.Error(e.message ?: "An unknown error occurred")
		}
	}*/

	suspend fun getCurrencies(endpoint: String = ApiRoutes.GET_CURRENCIES): CurrenciesDto {
		val url = "$endpoint.json"
		val response = client.get(url)
		return response.body()
	}

	suspend fun getRatesForEUR(
		endpoint: String = ApiRoutes.GET_RATES,
		baseCurrency: String = Constants.COUNTRY_CODE_EUR
	): RatesEurDto {
		val url = String.format(endpoint, baseCurrency) + ".json"
		val response = client.get(url)
		return response.body()
	}

	/*suspend fun getRatesForEUR(
		endpoint: String = ApiRoutes.GET_RATES,
		baseCurrency: String = Constants.COUNTRY_CODE_EUR
	): NetworkResult<RatesEurDto> {
		return try {
			val url = String.format(endpoint, baseCurrency) + ".json"
			val response = client.get(url)
			NetworkResult.Success(response.body())
		} catch (e: Exception) {
			NetworkResult.Error(e.message ?: "An unknown error occurred")
		}
	}*/


}