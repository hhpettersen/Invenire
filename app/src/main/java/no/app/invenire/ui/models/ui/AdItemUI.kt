package no.app.invenire.ui.models.ui

import no.app.invenire.ui.models.network.AdItem
import no.app.invenire.ui.models.network.AdType
import no.app.invenire.util.NumberFormatter

typealias Ads = List<AdItemUI>

data class AdItemUI(
    val description: String?,
    val id: String,
    val adType: AdType,
    val price: String?,
    val location: String?,
    val imageUrl: String?,
    val isFavorite: Boolean,
)

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
