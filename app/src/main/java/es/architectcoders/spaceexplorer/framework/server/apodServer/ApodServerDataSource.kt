package es.architectcoders.spaceexplorer.framework.server.apodServer

import android.util.Log
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import es.architectcoders.data.datasource.ApodRemoteDataSource
import es.architectcoders.domain.Apod
import es.architectcoders.domain.Error
import es.architectcoders.spaceexplorer.framework.toDomain
import es.architectcoders.spaceexplorer.framework.toError
import javax.inject.Inject

class ApodServerDataSource @Inject constructor(
    private val apiClient: ApodApiClient
) : ApodRemoteDataSource {
    override suspend fun getApod(): Either<Error, Apod?> = try {
        apiClient.getApod().body()?.toDomain().right()
    } catch (e: Exception) {
        Log.e("RoversViewModel", e.stackTraceToString())
        e.toError().left()
    }
}