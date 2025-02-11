package com.absut.currencyconverter.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.absut.currencyconverter.domain.model.Currencies
import com.absut.currencyconverter.domain.model.Rates
import com.absut.currencyconverter.domain.repository.CurrencyRepository
import com.bjsindia.bjscommunity.data.remote.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
	private val repository: CurrencyRepository
) : ViewModel() {

	private val _currencies = MutableStateFlow<NetworkResult<Currencies>?>(null)
	val currencies : StateFlow<NetworkResult<Currencies>?> = _currencies

	private val _rates = MutableStateFlow<NetworkResult<Rates>?>(null)
	val rates: StateFlow<NetworkResult<Rates>?> = _rates

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


}