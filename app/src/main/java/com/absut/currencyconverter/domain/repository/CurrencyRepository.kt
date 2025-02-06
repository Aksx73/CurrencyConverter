package com.absut.currencyconverter.domain.repository

import com.absut.currencyconverter.domain.model.Currencies
import com.absut.currencyconverter.domain.model.Rates
import com.bjsindia.bjscommunity.data.remote.NetworkResult

interface CurrencyRepository {

	suspend fun getCurrencies(): NetworkResult<Currencies>

	suspend fun getRatesForEUR(): NetworkResult<Rates>
}