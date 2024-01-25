package es.architectcoders.data.source.network


import es.architectcoders.framework.model.ApodApiClient
import es.architectcoders.framework.model.ApodResponse
import javax.inject.Inject

class ApodRemoteDataSource @Inject constructor(
    private val apiClient: ApodApiClient
) {
    suspend fun getApod(): ApodResponse? {
        return apiClient.getApod().body()
    }
}