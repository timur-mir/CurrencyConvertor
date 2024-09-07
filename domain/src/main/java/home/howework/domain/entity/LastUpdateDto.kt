package home.howework.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LastUpdateDto(
    @Json(name="milliseconds")
     var milliseconds: Long?
)
