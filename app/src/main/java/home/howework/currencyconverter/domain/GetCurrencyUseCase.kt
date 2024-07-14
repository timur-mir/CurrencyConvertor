package home.howework.currencyconverter.domain

import home.howework.currencyconverter.data.CurrencyConverterRepo
import home.howework.currencyconverter.entity.CurrencyInfoDto
import home.howework.currencyconverter.entity.PayLoadDto

class GetCurrencyUseCase(private val currencyConverterRepo: CurrencyConverterRepo) {
    suspend fun execute(from:String,to:String): CurrencyInfoDto? {
        currencyConverterRepo.apply {
            return currencyConverterRepo.getCurrency(from,to)
        }
    }
}