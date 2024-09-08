package home.howework.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LastUpdateResponse(
    @Json(name="milliseconds")
     var milliseconds: Long?
)
