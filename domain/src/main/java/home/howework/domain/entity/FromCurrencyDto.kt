package home.howework.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class FromCurrencyDto(
     var code: Int?,
     var name: String?,
     var strCode: String?
)
