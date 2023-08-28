package no.app.invenire.repository

import no.app.invenire.datasource.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) {
    suspend fun getAds() = remoteDataSource.getAds()
}
