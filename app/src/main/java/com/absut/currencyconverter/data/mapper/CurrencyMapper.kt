package com.absut.currencyconverter.data.mapper

import com.absut.currencyconverter.data.remote.dto.CurrenciesDto
import com.absut.currencyconverter.data.remote.dto.CurrencyRateDto
import com.absut.currencyconverter.data.remote.dto.RatesEurDto
import com.absut.currencyconverter.domain.model.Currencies
import com.absut.currencyconverter.domain.model.Currency
import com.absut.currencyconverter.domain.model.CurrencyRate
import com.absut.currencyconverter.domain.model.Rates
import com.absut.currencyconverter.util.Constants.COUNTRY_CODE_EUR
import com.absut.currencyconverter.util.Constants.COUNTRY_CODE_USD
import com.absut.currencyconverter.util.Util
import kotlin.reflect.full.memberProperties

fun CurrenciesDto.toCurrencies(): Currencies {
	val currency = mutableListOf<Currency>()
	for (property in CurrenciesDto::class.memberProperties) {
		val value = property.get(this) as? String ?: continue
		currency.add(Currency(code = property.name, name = value))
	}
	return Currencies(currency)
}

/*fun CurrencyRateDto.toCurrencyRate(code: String): List<CurrencyRate> {
	val currencyRate = mutableListOf<CurrencyRate>()
	for (property in CurrencyRateDto::class.memberProperties) {
		val value = property.get(this) as? Double ?: continue
		currencyRate.add(CurrencyRate(baseCurrencyCode = code, code = property.name, value = value))
	}
	return currencyRate
}*/

fun RatesEurDto.toCurrencyRates(): Rates {
	val baseCurrencyCode = COUNTRY_CODE_EUR
	val date = Util.getTimeMillisFromDate(this.date)
	val currencyRateDto = RatesEurDto::class.memberProperties.find {
		it.name == COUNTRY_CODE_EUR
	}?.get(this) as CurrencyRateDto

	val currencyRate = mutableListOf<CurrencyRate>()
	for (property in CurrencyRateDto::class.memberProperties) {
		val value = property.get(currencyRateDto) as? Double ?: continue
		currencyRate.add(
			CurrencyRate(
				date = date,
				code = property.name,
				value = value,
				baseCurrencyCode = baseCurrencyCode
			)
		)
	}
	return Rates(currencyRate)
}

/*fun RatesEurDto.toCurrencyRates(): Rates {
	val baseCurrencyCode = COUNTRY_CODE_EUR
	val currencyRateDto = RatesEurDto::class.memberProperties.find {
		it.name == COUNTRY_CODE_EUR
	}?.get(this) as CurrencyRateDto


	return Rates(
		date = Util.getTimeMillisFromDate(this.date),
		baseCurrencyCode = baseCurrencyCode,
		rates = currencyRateDto.toCurrencyRate(baseCurrencyCode)
	)
}*/

