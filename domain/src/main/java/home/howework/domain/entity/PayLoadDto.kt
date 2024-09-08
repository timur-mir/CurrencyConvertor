package home.howework.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class PayLoadDto(
    var lastUpdate: LastUpdateDto?,
    var rates: List<RatesDto>
)
