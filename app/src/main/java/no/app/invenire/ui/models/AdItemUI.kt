package no.app.invenire.ui.models

import models.AdItem
import models.AdType
import no.app.invenire.database.models.AdItemEntity
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

fun AdItemUI.toEntityModel(): AdItemEntity =
    AdItemEntity(
        id = id,
        description = description,
        adType = adType,
        price = price,
        location = location,
        imageUrl = imageUrl,
    )

fun List<AdItemUI>.toEntityModels(): List<AdItemEntity> = this.map { it.toEntityModel() }

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
