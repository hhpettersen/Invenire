package no.app.invenire.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import no.app.invenire.database.AppDatabase
import no.app.invenire.datasource.RemoteDataSource
import no.app.invenire.repository.implementation.AdRepository
import no.app.invenire.ui.models.cache.toUiModels
import no.app.invenire.ui.models.network.toUiModels
import no.app.invenire.ui.models.ui.AdItemUI
import no.app.invenire.ui.models.ui.Ads
import no.app.invenire.ui.models.ui.toEntityModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val appDatabase: AppDatabase,
): AdRepository {
    override suspend fun getAds(): List<AdItemUI> {
        val cachedAds = appDatabase.adItemDao().getAll().toUiModels()

        return try {
            val remoteAdResponse = withContext(Dispatchers.IO) {
                remoteDataSource.getAds()
            }
            return if (remoteAdResponse.isSuccessful) {
                val remoteAds = remoteAdResponse.body()?.items?.toUiModels() ?: return cachedAds
                mergeCacheAndRemote(cachedAds, remoteAds)
            } else {
                cachedAds
            }
        } catch (e: Exception) {
            cachedAds
        }
    }

    override suspend fun insertAd(item: AdItemUI) {
        appDatabase.adItemDao().insert(item.toEntityModel())
    }

    override suspend fun removeAd(id: String) {
        appDatabase.adItemDao().deleteById(id)
    }

    /**
     * Merges cached and remote ads. For each remote ad, it retains the `isFavorite` flag from the cached version
     * if present. This ensures that the ads are shown in the correct order if the ad exists in both cache and remote.
     * Ads unique to the cache are appended to the result.
     */
    private suspend fun mergeCacheAndRemote(
        cachedAds: Ads,
        remoteAds: Ads,
    ): Ads = withContext(Dispatchers.Default) {
        val transformedRemoteAds = remoteAds.map { remote ->
            val cache = cachedAds.firstOrNull { it.id == remote.id }
            cache?.let { remote.copy(isFavorite = it.isFavorite) } ?: remote
        }
        val cachedNotInRemote = cachedAds.filter { cached -> remoteAds.none { it.id == cached.id } }
        return@withContext transformedRemoteAds + cachedNotInRemote
    }
}
