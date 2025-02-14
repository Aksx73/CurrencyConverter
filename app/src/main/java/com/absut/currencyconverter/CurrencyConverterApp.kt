package com.absut.currencyconverter

import android.app.Application
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.absut.currencyconverter.data.local.CurrencyDatabase
import com.absut.currencyconverter.data.remote.ApiService
import com.absut.currencyconverter.data.remote.ktorHttpClient
import com.absut.currencyconverter.data.repository.CurrencyRepositoryImpl
import com.absut.currencyconverter.domain.repository.CurrencyRepository
import com.absut.currencyconverter.ui.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel

import org.koin.dsl.module

class CurrencyConverterApp : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidLogger()
			androidContext(this@CurrencyConverterApp)
			modules(appModules)
		}
	}

}

/**
 * Single: Provides a single instance of a dependency throughout the application’s lifecycle.
 * Factory: Creates a new instance of a dependency each time it’s requested.
 * ViewModel: Specifically designed for Android ViewModel instances, ensuring proper lifecycle management.
 * */
val appModules = module {
	single {
		Room.databaseBuilder(
			androidContext(),
			CurrencyDatabase::class.java,
			"currency_converter_db"
		)
			.fallbackToDestructiveMigration()
			.build()
	}
	single { get<CurrencyDatabase>().currencyDao() }
	single { ktorHttpClient }
	single { ApiService(get()) }
	single<CurrencyRepository> { CurrencyRepositoryImpl(get(), get()) }
	viewModel { MainViewModel(get()) }

}