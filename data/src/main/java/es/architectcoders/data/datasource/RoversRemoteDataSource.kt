package es.architectcoders.data.datasource

import arrow.core.Either
import es.architectcoders.domain.Photo
import es.architectcoders.domain.Error
import java.util.Calendar

interface RoversRemoteDataSource {

    suspend fun getRovers(date: Calendar): Either<Error, List<Photo>>
}