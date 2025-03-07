package com.absut.currencyconverter.ui.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.absut.currencyconverter.ui.viewmodel.MainViewModel

@Composable
fun AppNavHost(navController: NavHostController,viewModel: MainViewModel) {
	NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
		composable(route = Routes.HomeScreen.route) {
			HomeScreen(navController = navController,viewModel = viewModel)
		}
		composable(route = Routes.CurrencyListScreen.route) {
			CurrencyListScreen(viewModel = viewModel)
		}
	}
}

sealed class Routes(val route: String) {
	object HomeScreen : Routes("home")
	object CurrencyListScreen : Routes("currency_list")
}