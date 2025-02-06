package com.absut.currencyconverter.data.repository

import com.absut.currencyconverter.data.mapper.toCurrencies
import com.absut.currencyconverter.data.mapper.toCurrencyRates
import com.absut.currencyconverter.data.remote.ApiService
import com.absut.currencyconverter.domain.model.Currencies
import com.absut.currencyconverter.domain.model.Rates
import com.absut.currencyconverter.domain.repository.CurrencyRepository
import com.bjsindia.bjscommunity.data.remote.NetworkResult

class CurrencyRepositoryImpl(
	private val api: ApiService
) : CurrencyRepository {

	override suspend fun getCurrencies(): NetworkResult<Currencies> {
		return try {
			val data = api.getCurrencies().toCurrencies()
			NetworkResult.Success(data)
		} catch (e: Exception) {
			NetworkResult.Error(e.message ?: "An unknown error occurred")
		}
	}

	override suspend fun getRatesForEUR(): NetworkResult<Rates> {
		return try {
			val data = api.getRatesForEUR().toCurrencyRates()
			NetworkResult.Success(data)
		} catch (e: Exception) {
			NetworkResult.Error(e.message ?: "An unknown error occurred")
		}
	}

}