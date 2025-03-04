package com.absut.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.absut.currencyconverter.ui.theme.CurrencyConverterTheme
import com.absut.currencyconverter.ui.viewmodel.MainViewModel
import com.absut.currencyconverter.ui.views.HomeScreen
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			CurrencyConverterTheme {
				HomeScreen(viewModel = koinViewModel<MainViewModel>())
			}
		}
	}
}