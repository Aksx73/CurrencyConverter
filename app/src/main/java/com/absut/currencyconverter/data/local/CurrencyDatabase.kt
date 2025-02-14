package com.absut.currencyconverter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.absut.currencyconverter.domain.model.Currency
import com.absut.currencyconverter.domain.model.Rates

@Database(
    entities = [Currency::class, Rates::class],
    version = 1,
    exportSchema = false
)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}