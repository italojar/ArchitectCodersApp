package es.architectcoders.data.repository

import es.architectcoders.data.datasource.ApodLocalDataSource
import es.architectcoders.data.datasource.ApodRemoteDataSource
import es.architectcoders.domain.Apod
import es.architectcoders.domain.Error
import javax.inject.Inject

class ApodRepository @Inject constructor(
    private val apodLocalDataSource: ApodLocalDataSource,
    private val apodRemoteDataSource: ApodRemoteDataSource
) {
    val allApods get() = apodLocalDataSource.getApods
    suspend fun requestApod(): Error? {
        val apod = apodRemoteDataSource.getApod()
        apod.fold(
            ifLeft = { error -> return error },
            ifRight = { apodResponse ->
            val apodExist = apodLocalDataSource.apodExists(apodResponse)
            if (!apodExist)  {
                apodLocalDataSource.saveApod(apodResponse)
            }
        })
        return null
    }

    suspend fun saveApodAsFavourite(apod: Apod): Error? {
        return apodLocalDataSource.saveApodAsFavourite(apod)
    }
}