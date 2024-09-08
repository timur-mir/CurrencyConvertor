package home.howework.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyInfoResponse(
    @Json(name="trackingId")
 var trackingId: String?,
    @Json(name="resultCode")
 var resultCode: String?,
    @Json(name="payload")
  var payload: PayLoadResponse
)