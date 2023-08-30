package no.app.invenire.repository

import no.app.invenire.repository.implementation.AdRepository
import no.app.invenire.ui.models.AdItemUI

class TestAdRepositoryImpl(
    private val ads: List<AdItemUI>,
) : AdRepository {
    val cachedAds = mutableListOf<AdItemUI>()

    override suspend fun getAds(): List<AdItemUI> = ads

    override suspend fun insertAd(item: AdItemUI) {
        cachedAds.add(item)
    }

    override suspend fun removeAd(id: String) {
        cachedAds.removeIf { it.id == id }
    }
}
