package es.architectcoders.spaceexplorer.framework.database.roverDb

import es.architectcoders.data.datasource.RoversLocalDataSource
import es.architectcoders.domain.Photo
import es.architectcoders.spaceexplorer.framework.toDomain
import es.architectcoders.domain.Error
import es.architectcoders.spaceexplorer.framework.toEntity
import es.architectcoders.spaceexplorer.framework.toError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoversRoomDataSource @Inject constructor(
    private val roversDao: RoversDao
) : RoversLocalDataSource {

    override val getPhoto: Flow<List<Photo>> = roversDao.getAllRovers().map {
        it.toDomain() }

    override suspend fun saveRovers(rovers: List<Photo>): Error? = try {
        rovers.toEntity().let { roversEntity -> roversDao.insertRovers(roversEntity) }
        null
    } catch (e: Exception) {
        e.toError()
    }

    override suspend fun isRoversEmpty(): Boolean = roversDao.roversCount() == 0

//    override suspend fun saveRoversAsFavourite(photo: Photo): Error? = try {
//        photo.toEntity().let { roversEntity -> roversDao.updateRovers(roversEntity) }
//        null
//    } catch (e: Exception) {
//        e.toError()
//    }

}

fun List<PhotoEntity>.toDomain() : List<Photo> = map { it.toDomain() }



