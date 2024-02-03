package es.architectcoders.data.repository

import es.architectcoders.data.datasource.RoversLocalDataSource
import es.architectcoders.data.datasource.RoversRemoteDataSource
import es.architectcoders.domain.Photo
import es.architectcoders.domain.Error
import javax.inject.Inject

class RoversRepository @Inject constructor(
private val roversLocalDataSource: RoversLocalDataSource,
private val roversRemoteDataSource: RoversRemoteDataSource){

    val allRovers get() = roversLocalDataSource.getPhoto

    suspend fun requestRovers(date: String): Error? {
        val rovers = roversRemoteDataSource.getRovers(date)
        rovers.fold(
            ifLeft = { error -> return error },
            ifRight = { roversResponse ->
                roversResponse?.let { roversLocalDataSource.saveRovers(it) }
            })
        return null
    }

    suspend fun saveRoversAsFavourite(photo: Photo): Error? {
        return roversLocalDataSource.saveRoversAsFavourite(photo)
    }
}