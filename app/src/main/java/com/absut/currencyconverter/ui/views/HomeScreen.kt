package com.absut.currencyconverter.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.absut.currencyconverter.data.util.Resource
import com.absut.currencyconverter.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {

	val currencyState = viewModel.currenciesState.collectAsState()
	val currencies = viewModel.currenciesFromDB.collectAsState()

	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text(text = "Currency Converter") },
			)
		}
	) { innerPadding ->
		Column(modifier = modifier.padding(innerPadding)) {
			when (currencyState.value) {
				is Resource.Loading -> {
					Text(text = "Loading...")
				}

				is Resource.Error -> {
					Text(text = "Error: ${currencyState.value.message}")
				}

				is Resource.Success -> {
					//Text(text = "Success")
					LazyColumn {
						items(currencies.value){ currency ->
							CurrencyListItem(currency = currency, onClick = {})
						}
					}
				}
			}
		}
	}
}

@Preview
@Composable
private fun HomeScreenPreview() {
	Surface {
		HomeScreen(viewModel = viewModel<MainViewModel>())
	}
}
