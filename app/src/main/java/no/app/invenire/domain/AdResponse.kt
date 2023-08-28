package no.app.invenire.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdResponse(
    val items: List<AdItem>,
)

@JsonClass(generateAdapter = true)
data class AdItem(
    val description: String?,
    val id: String,
    @Json(name = "ad-type")
    val adType: AdType,
    val price: Price?,
    val location: String?,
    val image: AdImage?,
)

@JsonClass(generateAdapter = true)
data class Price(
    val value: Int,
)

@JsonClass(generateAdapter = true)
data class AdImage(
    val url: String,
    val height: Int,
    val width: Int,
)

enum class AdType {
    BAP,
    REALESTATE,
    B2B,
    UNKNOWN;
}
