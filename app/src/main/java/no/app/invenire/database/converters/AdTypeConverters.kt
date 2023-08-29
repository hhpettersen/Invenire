package no.app.invenire.database.converters

import androidx.room.TypeConverter
import no.app.invenire.ui.models.network.AdType

object AdTypeConverters {
    @TypeConverter
    @JvmStatic
    fun fromAdType(type: AdType): String = type.name

    @TypeConverter
    @JvmStatic
    fun toAdType(value: String): AdType = AdType.valueOf(value)
}
