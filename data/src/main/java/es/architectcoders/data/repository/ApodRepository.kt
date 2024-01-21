package es.architectcoders.data.repository

import es.architectcoders.data.source.local.ApodLocalDataSource
import es.architectcoders.data.source.local.model.ApodData
import es.architectcoders.data.source.network.model.ApodResponse
import es.architectcoders.data.source.network.ApodRemoteDataSource
import es.architectcoders.data.source.network.model.Error
import es.architectcoders.data.source.network.model.toError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApodRepository @Inject constructor(
    private val apodLocalDataSource: ApodLocalDataSource,
    private val apodRemoteDataSource: ApodRemoteDataSource
) {
    suspend fun requestApod(): Error? = try {
        val apod = apodRemoteDataSource.getApod()
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

    fun getApods(): Flow<List<ApodData>> {
        return apodLocalDataSource.getApods()
    }

    suspend fun saveApodAsFavourite(apodData: ApodData): Error? = try {
        apodLocalDataSource.saveApodAsFavourite(apodData)
        null // return null if no error
    } catch (exception: Exception) {
        exception.toError()
    }
}