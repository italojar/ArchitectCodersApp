package es.architectcoders.data.source.network.service

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApiClient {

    @GET("planetary/apod")
    suspend fun getApod(
        @Query("api_key") apiKey: String
    ): JsonObject
}