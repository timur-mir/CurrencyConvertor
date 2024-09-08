package home.howework.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FromCurrencyResponse(
    @Json(name="code")
     var code: Int?,
    @Json(name="name")
     var name: String?,
    @Json(name="strCode")
     var strCode: String?
)
