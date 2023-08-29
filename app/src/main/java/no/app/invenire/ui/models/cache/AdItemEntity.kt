package no.app.invenire.ui.models.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import no.app.invenire.ui.models.network.AdType

@Entity(tableName = "ad_item")
data class AdItemEntity(
    @PrimaryKey val id: String,
    val description: String?,
    val adType: AdType,
    val price: Int?,
    val location: String?,
    val imageUrl: String?,
)
