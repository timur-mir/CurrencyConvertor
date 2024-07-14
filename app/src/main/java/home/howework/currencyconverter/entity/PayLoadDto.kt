package home.howework.currencyconverter.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PayLoadDto(
    @Json(name="lastUpdate")
    var lastUpdate: LastUpdateDto?,
    @Json(name="rates")
    var rates: List<RatesDto>
)
