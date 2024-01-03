package es.architectcoders.data.source.network.service

import es.architectcoders.data.source.network.model.ApodResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApiClient {

    @GET("planetary/apod")
    suspend fun getApod(@Query("api_key") apiKey: String): Response<ApodResponse>
}