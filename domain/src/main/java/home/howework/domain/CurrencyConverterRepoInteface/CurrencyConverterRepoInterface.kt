package home.howework.domain.CurrencyConverterRepoInteface

import home.howework.domain.entity.CurrencyInfoDto

interface CurrencyConverterRepoInterface {
    suspend fun getCurrency(from:String,to:String): CurrencyInfoDto
}