package com.absut.currencyconverter

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
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
    single { }
    //singleOf()
    //factoryOf()
    //viewModelOf()

}