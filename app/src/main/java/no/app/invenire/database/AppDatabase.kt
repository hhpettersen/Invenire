package no.app.invenire.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import no.app.invenire.database.converters.AdTypeConverters
import no.app.invenire.database.dao.AdItemDao
import no.app.invenire.ui.models.cache.AdItemEntity

@Database(entities = [AdItemEntity::class], version = 1, exportSchema = false)
@TypeConverters(AdTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun adItemDao(): AdItemDao
}
