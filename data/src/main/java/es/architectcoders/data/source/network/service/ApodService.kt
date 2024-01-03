package es.architectcoders.data.source.network.service


import com.google.gson.JsonObject
import es.architectcoders.data.source.network.model.ApodResponse
import javax.inject.Inject

class ApodService @Inject constructor(
    private val apiClient: ApodApiClient
) {
    suspend fun getApod(): ApodResponse? {
        return apiClient.getApod("X2fTBf6Rjq8qA6Bz8AF43hSsloWps7A8RHLulOq3").body()
    }
}