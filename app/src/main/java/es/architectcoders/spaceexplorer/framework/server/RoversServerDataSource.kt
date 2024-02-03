package es.architectcoders.spaceexplorer.framework.server

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import es.architectcoders.data.datasource.RoversRemoteDataSource
import es.architectcoders.domain.Photo
import es.architectcoders.domain.Error
import es.architectcoders.spaceexplorer.framework.toDomain
import es.architectcoders.spaceexplorer.framework.toError
import es.architectcoders.spaceexplorer.framework.tryCall
import javax.inject.Inject

class RoversServerDataSource @Inject constructor(
    private val apiClient: RoversApiClient
) : RoversRemoteDataSource {
    override suspend fun getRovers(date: String): Either<Error, List<Photo>?> = tryCall {
        apiClient.getRovers(date).body()?.photos?.toDomain()
    }
}