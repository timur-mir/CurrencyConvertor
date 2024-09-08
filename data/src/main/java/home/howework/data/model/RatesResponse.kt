package home.howework.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RatesResponse(
    @Json(name="category")
    var category: String?,
    @Json(name="fromCurrency")
    var fromCurrency: FromCurrencyResponse?,
    @Json(name="toCurrency")
    var toCurrency: ToCurrencyResponse?,
    @Json(name="buy")
    var buy: Double?,
    @Json(name="sell")
    var sell: Double?
)