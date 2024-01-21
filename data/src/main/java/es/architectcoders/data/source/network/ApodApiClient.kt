package es.architectcoders.data.source.network

import es.architectcoders.data.source.network.model.ApodResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApodApiClient {

    @GET("planetary/apod")
    suspend fun getApod(): Response<ApodResponse>
}