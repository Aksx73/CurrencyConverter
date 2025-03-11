package com.absut.currencyconverter.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.absut.currencyconverter.data.util.Resource
import com.absut.currencyconverter.ui.components.CurrencyListItem
import com.absut.currencyconverter.ui.viewmodel.CurrencyType
import com.absut.currencyconverter.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CurrencyListScreen(modifier: Modifier = Modifier, viewModel: MainViewModel, navController: NavController) {
	var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

	val currencyState = viewModel.currenciesState.collectAsState()
	val currencies = viewModel.currenciesFromDB.collectAsState()

	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					TextField(
						value = searchQuery,
						onValueChange = { searchQuery = it },
						placeholder = { Text("Search...") },
						modifier = Modifier.fillMaxWidth(),
						trailingIcon = {
							IconButton(onClick = { searchQuery = TextFieldValue("") }) {
								Icon(
									imageVector = Icons.Default.Close,
									contentDescription = "Close"
								)
							}
						},
						colors = TextFieldDefaults.colors(
							focusedContainerColor = Color.Transparent,
							unfocusedContainerColor = Color.Transparent,
							disabledContainerColor = Color.Transparent,
							focusedIndicatorColor = Color.Transparent,
							unfocusedIndicatorColor = Color.Transparent
						)
					)
				},
				navigationIcon = {
					IconButton(onClick = {
						navController.navigateUp()
					}) {
						Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
					}
				},
				scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
			)

		}) { innerPadding ->
		Column(
			modifier = modifier
				.padding(innerPadding)
		) {
			when (currencyState.value) {
				is Resource.Loading -> {
					Text(text = "Loading...")
				}

				is Resource.Error -> {
					Text(text = "Error: ${currencyState.value.message}")
				}

				is Resource.Success -> {
					LazyColumn {
						items(currencies.value) { currency ->
							CurrencyListItem(currency = currency, onClick = {
								when (viewModel.getSelectedCurrencyType()) {
									CurrencyType.FROM ->
										viewModel.currentSelectedCurrencyFrom = currency.code

									CurrencyType.TO ->
										viewModel.currentSelectedCurrencyTo = currency.code
								}
								navController.navigateUp()
							})
						}
					}
				}
			}
		}
	}
}