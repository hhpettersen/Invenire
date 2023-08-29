package no.app.invenire.ui.models.ui

import no.app.invenire.ui.models.cache.AdItemEntity
import no.app.invenire.ui.models.network.AdType

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
