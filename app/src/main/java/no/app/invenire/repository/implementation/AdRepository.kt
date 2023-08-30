package no.app.invenire.repository.implementation

import no.app.invenire.ui.models.AdItemUI

interface AdRepository {
    suspend fun getAds(): List<AdItemUI>

    suspend fun insertAd(item: AdItemUI)

    suspend fun removeAd(id: String)
}
