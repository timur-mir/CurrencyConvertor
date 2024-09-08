package home.howework.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class RatesDto(
    var category: String?,
    var fromCurrency: FromCurrencyDto?,
    var toCurrency: ToCurrencyDto?,
    var buy: Double?,
    var sell: Double?
)