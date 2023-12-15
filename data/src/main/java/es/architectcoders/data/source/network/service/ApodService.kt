package es.architectcoders.data.source.network.service


import com.google.gson.JsonObject
import javax.inject.Inject

class ApodService @Inject constructor(
    private val apiClient: ApodApiClient
) {
    suspend fun getApod(): JsonObject {
        return apiClient.getApod("")
    }
}