package home.howework.currencyconverter.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyInfoDto(
    @Json(name="trackingId")
 var trackingId: String?,
    @Json(name="resultCode")
 var resultCode: String?,
    @Json(name="payload")
  var payload:PayLoadDto
)