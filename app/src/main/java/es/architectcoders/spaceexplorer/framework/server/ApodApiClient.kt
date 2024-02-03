package es.architectcoders.spaceexplorer.framework.server

import retrofit2.Response
import retrofit2.http.GET

interface ApodApiClient {

    @GET("planetary/apod")
    suspend fun getApod(): Response<ApodResponse>
}