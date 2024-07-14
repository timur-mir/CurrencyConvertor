package home.howework.currencyconverter.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RatesDto(
    @Json(name="category")
    var category: String?,
    @Json(name="fromCurrency")
    var fromCurrency: FromCurrencyDto?,
    @Json(name="toCurrency")
    var toCurrency: ToCurrencyDto?,
    @Json(name="buy")
    var buy: Double?,
    @Json(name="sell")
    var sell: Double?
)