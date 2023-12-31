package no.app.network.datasource

import models.AdResponse
import retrofit2.Response
import retrofit2.http.GET

interface RemoteDataSource {

    @GET("6a1bcc8f429dcdb8f9196e917e5138bd/raw/discover.json")
    suspend fun getAds(): Response<AdResponse>
}
