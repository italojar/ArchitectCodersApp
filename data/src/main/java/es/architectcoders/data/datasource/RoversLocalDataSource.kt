package es.architectcoders.data.datasource

import es.architectcoders.domain.Photo
import es.architectcoders.domain.Error
import kotlinx.coroutines.flow.Flow

interface RoversLocalDataSource {

    val getPhoto: Flow<List<Photo>>

    suspend fun saveRovers(rovers: List<Photo>): Error?

    suspend fun isRoversEmpty(): Boolean

//    suspend fun saveRoversAsFavourite(photos: List<Photo>): Error?
}