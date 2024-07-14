package home.howework.currencyconverter.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FromCurrencyDto(
    @Json(name="code")
     var code: Int?,
    @Json(name="name")
     var name: String?,
    @Json(name="strCode")
     var strCode: String?
)
