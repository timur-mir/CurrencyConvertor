package home.howework.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PayLoadResponse(
    @Json(name="lastUpdate")
    var lastUpdate: LastUpdateResponse?,
    @Json(name="rates")
    var rates: List<RatesResponse>
)
