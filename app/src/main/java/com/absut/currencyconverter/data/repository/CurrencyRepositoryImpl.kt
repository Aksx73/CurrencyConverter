package com.absut.currencyconverter.data.repository

import com.absut.currencyconverter.data.local.CurrencyDao
import com.absut.currencyconverter.data.mapper.toCurrencies
import com.absut.currencyconverter.data.mapper.toCurrencyRates
import com.absut.currencyconverter.data.remote.ApiService
import com.absut.currencyconverter.data.util.Resource
import com.absut.currencyconverter.domain.model.Currencies
import com.absut.currencyconverter.domain.model.Currency
import com.absut.currencyconverter.domain.model.Rates
import com.absut.currencyconverter.domain.repository.CurrencyRepository
import com.bjsindia.bjscommunity.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrencyRepositoryImpl(
	private val api: ApiService,
	private val dao: CurrencyDao
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

	override fun getLocalCurrencies(): Flow<List<Currency>> {
		return dao.getCurrencies()
	}

	override suspend fun getLocalRates(): Rates {
		return dao.getRatesForEUR()
	}

	override suspend fun insertCurrencies(currencies: List<Currency>) {
		return dao.insertCurrencies(currencies)
	}

	override suspend fun insertRates(rates: Rates) {
		return dao.insertRates(rates)
	}

	override suspend fun fetchAndSaveCurrencies(): Flow<Resource<Unit>> = flow {
		try {
			val data = api.getCurrencies().toCurrencies()
			dao.insertCurrencies(data.currency)
			emit(Resource.Success(Unit))
		} catch (e: Exception) {
			emit(Resource.Error(e.message ?: "An unknown error occurred"))
		}
	}

	override suspend fun fetchAndSaveRatesForEUR(): Flow<Resource<Unit>> = flow {
		try {
			val data = api.getRatesForEUR().toCurrencyRates()
			dao.insertRates(data.rate)
			emit(Resource.Success(Unit))
		} catch (e: Exception) {
			emit(Resource.Error(e.message ?: "An unknown error occurred"))
		}
	}

	override suspend fun getCurrencyRate(
		baseCurrency: String,
		targetCurrency: String
	): CurrencyRate {
		return dao.getRate(targetCurrency, baseCurrency)
	}
}