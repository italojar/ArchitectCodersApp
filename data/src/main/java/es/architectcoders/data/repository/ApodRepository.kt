package es.architectcoders.data.repository

import es.architectcoders.data.source.local.ApodLocalDataSource
import es.architectcoders.data.source.network.ApodRemoteDataSource
import es.architectcoders.data.source.network.model.ApodResponse
import es.architectcoders.data.source.network.model.toError
import es.architectcoders.domain.model.Apod
import es.architectcoders.domain.model.Error
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApodRepository @Inject constructor(
    private val apodLocalDataSource: ApodLocalDataSource,
    private val apodRemoteDataSource: ApodRemoteDataSource
) {
    suspend fun requestApod(): Error? = try {
        val apod: ApodResponse? = apodRemoteDataSource.getApod()
        if (apod != null) {
            val apodExist = apodLocalDataSource.apodExists(apod)
            if (!apodExist)  {
                apodLocalDataSource.saveApod(apod)
            }
        }
        null // return null if no error
    } catch (exception: Exception) {
        exception.toError()
    }

    fun getApods(): Flow<List<Apod>> {
        return apodLocalDataSource.getApods()
    }

    suspend fun saveApodAsFavourite(apod: Apod): Error? = try {
        apodLocalDataSource.saveApodAsFavourite(apod)
        null // return null if no error
    } catch (exception: Exception) {
        exception.toError()
    }
}