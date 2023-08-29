package no.app.invenire.ui.models.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import no.app.invenire.ui.models.ui.AdItemUI
import no.app.invenire.util.NumberFormatter

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
)

enum class AdType {
    BAP,
    REALESTATE,
    B2B,
    UNKNOWN;
}

fun List<AdItem>.toUiModels(): List<AdItemUI> = map { it.toUiModel() }

fun AdItem.toUiModel(): AdItemUI =
    AdItemUI(
        description = description,
        id = id,
        adType = adType,
        price = price?.value?.let { NumberFormatter.formatWithSpaces(it) },
        location = location,
        imageUrl = image?.url?.let { "https://images.finncdn.no/dynamic/480x360c/$it" },
        isFavorite = false
    )
