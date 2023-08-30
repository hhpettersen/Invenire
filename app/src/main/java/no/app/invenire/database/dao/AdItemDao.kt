package no.app.invenire.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import no.app.invenire.database.models.AdItemEntity

@Dao
interface AdItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(adItem: AdItemEntity)

    @Query("DELETE FROM ad_item WHERE id = :itemId")
    suspend fun deleteById(itemId: String)

    @Query("SELECT * FROM ad_item")
    suspend fun getAll(): List<AdItemEntity>

    @Query("DELETE FROM ad_item")
    suspend fun clearAll()
}
