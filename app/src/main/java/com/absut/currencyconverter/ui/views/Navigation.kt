package com.absut.currencyconverter.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(modifier: Modifier = Modifier) {
	val navController = rememberNavController()

	NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
		composable(route = Routes.HomeScreen.route) {
			HomeScreen(navController = navController)
		}
		composable(route = Routes.CurrencyListScreen.route) {
			CurrencyListScreen()
		}
	}
}

sealed class Routes(val route: String) {
	object HomeScreen : Routes("home")
	object CurrencyListScreen : Routes("currency_list")
}