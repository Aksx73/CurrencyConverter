package com.absut.currencyconverter.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.absut.currencyconverter.data.util.Resource
import com.absut.currencyconverter.domain.model.Currencies
import com.absut.currencyconverter.domain.model.Currency
import com.absut.currencyconverter.domain.model.Rates
import com.absut.currencyconverter.domain.repository.CurrencyRepository
import com.bjsindia.bjscommunity.data.remote.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
	private val repository: CurrencyRepository
) : ViewModel() {

	var currentSelectedCurrencyFrom: String = "USD"
	var currentSelectedCurrencyTo: String = "INR"

	private val _currencies = MutableStateFlow<NetworkResult<Currencies>?>(null)
	val currencies: StateFlow<NetworkResult<Currencies>?> = _currencies

	private val _currenciesState = MutableStateFlow<Resource<Unit>>(Resource.Loading())
	val currenciesState: StateFlow<Resource<Unit>> = _currenciesState

	private val _rates = MutableStateFlow<NetworkResult<Rates>?>(null)
	val rates: StateFlow<NetworkResult<Rates>?> = _rates

	init {
		fetchAndSaveCurrencies()
	}

	val currenciesFromDB = repository.getLocalCurrencies().stateIn(
		viewModelScope,
		started = SharingStarted.WhileSubscribed(5000),
		initialValue = emptyList()
	)

	fun getCurrencies() {
		viewModelScope.launch {
			_currencies.value = NetworkResult.Loading()
			_currencies.value = repository.getCurrencies()
		}
	}

	fun getRatesForEUR() {
		viewModelScope.launch {
			_rates.value = NetworkResult.Loading()
			_rates.value = repository.getRatesForEUR()
		}
	}

	//todo
	// check local for value of date when last updated
	// fetch currencies and its rate from remote api
	// save it in local db
	// access data from local db

	fun fetchAndSaveCurrencies() {
		viewModelScope.launch {
			repository.fetchAndSaveCurrencies().collect { result ->
				_currenciesState.value = result
			}
		}
	}

	fun getCurrenciesFromLocalDB() {
		viewModelScope.launch {
			repository.getLocalCurrencies()
		}
	}

	fun fetchAndSaveRatesForEUR() {
		viewModelScope.launch {
			/*repository.fetchAndSaveRatesForEUR().collect { result ->

			}*/
		}
	}

	fun getRatesForEURFromLocalDB() {
		/*viewModelScope.launch {
			repository.getLocalRatesForEUR()
		}*/
	}

}

enum class SelectedCurrency {
	FROM, TO
}