package home.howework.domain

import home.howework.domain.CurrencyConverterRepoInteface.CurrencyConverterRepoInterface
import home.howework.domain.entity.CurrencyInfoDto
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(private val currencyConverterRepo: CurrencyConverterRepoInterface) {
    suspend fun execute(from:String,to:String): CurrencyInfoDto {
            return currencyConverterRepo.getCurrency(from,to)
        }
    }
