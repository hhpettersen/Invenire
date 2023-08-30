package no.app.invenire.database.converters

import androidx.room.TypeConverter
import models.AdType

object AdTypeConverters {
    @TypeConverter
    @JvmStatic
    fun fromAdType(type: models.AdType): String = type.name

    @TypeConverter
    @JvmStatic
    fun toAdType(value: String): models.AdType = models.AdType.valueOf(value)
}
