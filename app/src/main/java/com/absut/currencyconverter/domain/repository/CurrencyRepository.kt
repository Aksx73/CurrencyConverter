package com.absut.currencyconverter.domain.repository

import com.absut.currencyconverter.data.util.Resource
import com.absut.currencyconverter.domain.model.Currencies
import com.absut.currencyconverter.domain.model.Currency
import com.absut.currencyconverter.domain.model.Rates
import com.bjsindia.bjscommunity.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

	suspend fun getCurrencies(): NetworkResult<Currencies>

	suspend fun getRatesForEUR(): NetworkResult<Rates>

	fun getLocalCurrencies(): Flow<List<Currency>>

	suspend fun getLocalRates(): Rates

	suspend fun insertCurrencies(currencies: List<Currency>)

	suspend fun insertRates(rates: Rates)

	suspend fun fetchAndSaveCurrencies(): Flow<Resource<Unit>>
}