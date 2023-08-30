package no.app.invenire.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import no.app.invenire.ui.models.AdItemUI

@Entity(tableName = "ad_item")
data class AdItemEntity(
    @PrimaryKey val id: String,
    val description: String?,
    val adType: models.AdType,
    val price: String?,
    val location: String?,
    val imageUrl: String?,
)

fun List<AdItemEntity>.toUiModels(): List<AdItemUI> = this.map { it.toUiModel() }

fun AdItemEntity.toUiModel(): AdItemUI =
    AdItemUI(
        description = description,
        id = id,
        adType = adType,
        price = price,
        location = location,
        imageUrl = imageUrl,
        isFavorite = true,
    )
