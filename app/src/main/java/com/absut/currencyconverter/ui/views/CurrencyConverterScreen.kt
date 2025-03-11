package com.absut.currencyconverter.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.absut.currencyconverter.ui.components.CurrencyInputField
import com.absut.currencyconverter.ui.components.FavoriteItem
import com.absut.currencyconverter.ui.viewmodel.CurrencyType
import com.absut.currencyconverter.ui.viewmodel.MainViewModel

@Composable
fun CurrencyConverterScreen(viewModel: MainViewModel, navController: NavController) {
	var fromCurrency by remember { mutableStateOf(viewModel.currentSelectedCurrencyFrom) }
	var toCurrency by remember { mutableStateOf(viewModel.currentSelectedCurrencyTo) }
	var fromAmount by remember { mutableStateOf(viewModel.fromAmount) }
	var toAmount by remember { mutableStateOf(viewModel.toAmount) }

	Column(
		modifier = Modifier
			.fillMaxSize()
	) {

		Spacer(modifier = Modifier.height(24.dp))

		// From Currency Input
		CurrencyInputField(
			selectedCurrency = fromCurrency,
			onCurrencyClick = {
				viewModel.setSelectedCurrencyType(CurrencyType.FROM)
				navController.navigate(Routes.CurrencyListScreen.route)
			},
			//onCurrencyChange = { fromCurrency = it },
			amount = fromAmount,
			onAmountChange = { fromAmount = it },
			placeholder = "1",
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp)
		)

		Spacer(modifier = Modifier.height(16.dp))

		// To Currency Input
		CurrencyInputField(
			selectedCurrency = toCurrency,
			onCurrencyClick = {
				viewModel.setSelectedCurrencyType(CurrencyType.TO)
				navController.navigate(Routes.CurrencyListScreen.route)
			},
			//onCurrencyChange = { toCurrency = it },
			amount = toAmount,
			onAmountChange = { toAmount = it },
			placeholder = "24.3",
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp)
		)

		Spacer(modifier = Modifier.height(32.dp))

		// Favorites Section
		Text(
			text = "Favourites",
			modifier = Modifier.padding(horizontal = 16.dp)
		)

		Spacer(modifier = Modifier.height(16.dp))

		// Favorites List
		LazyRow(
			horizontalArrangement = Arrangement.spacedBy(12.dp),
			contentPadding = PaddingValues(horizontal = 16.dp)
		) {
			// Sample favorite currency pairs
			val favorites = listOf(
				Pair("AED", "INR"),
				Pair("AED", "INR"),
				Pair("AED", "INR")
			)

			items(favorites) { pair ->
				FavoriteItem(fromCurrency = pair.first, toCurrency = pair.second) {

				}
			}
		}
	}
}


@Preview(showBackground = true)
@Composable
fun CurrencyConverterPreview() {
	MaterialTheme {
		CurrencyConverterScreen(viewModel = viewModel(), navController = rememberNavController())
	}
}