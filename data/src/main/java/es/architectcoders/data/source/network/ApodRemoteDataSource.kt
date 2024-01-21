package es.architectcoders.data.source.network


import es.architectcoders.data.source.network.model.ApodResponse
import javax.inject.Inject

class ApodRemoteDataSource @Inject constructor(
    private val apiClient: ApodApiClient
) {
    suspend fun getApod(): ApodResponse? {
        return apiClient.getApod().body()
    }
}