package es.architectcoders.spaceexplorer.framework.server.roverServer

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RoversApiClient {

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getRovers(
        @Query("earth_date") date: String,
        @Query("camera") camera: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<RoversResponse>
}