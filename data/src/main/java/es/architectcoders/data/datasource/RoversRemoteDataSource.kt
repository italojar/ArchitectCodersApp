package es.architectcoders.data.datasource

import arrow.core.Either
import es.architectcoders.domain.Photo
import es.architectcoders.domain.Error

interface RoversRemoteDataSource {

    suspend fun getRovers(date: String, camera: String, page: Int, apiKey: String):
            Either<Error, List<Photo>?>
}