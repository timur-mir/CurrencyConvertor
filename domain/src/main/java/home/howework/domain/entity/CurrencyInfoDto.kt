package home.howework.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class CurrencyInfoDto(
 var trackingId: String?,
 var resultCode: String?,
  var payload: PayLoadDto
)