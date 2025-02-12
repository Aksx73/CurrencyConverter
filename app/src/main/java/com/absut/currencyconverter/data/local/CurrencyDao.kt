package com.absut.currencyconverter.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.absut.currencyconverter.domain.model.Currencies
import com.absut.currencyconverter.domain.model.Currency
import com.absut.currencyconverter.domain.model.Rates
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

	@Query("SELECT * FROM currency")
	fun getCurrencies(): Flow<List<Currency>>

	@Query("SELECT * FROM eu_rates")
	suspend fun getRatesForEUR(): Rates

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertCurrencies(currencies: List<Currency>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertRates(rates: Rates)

}