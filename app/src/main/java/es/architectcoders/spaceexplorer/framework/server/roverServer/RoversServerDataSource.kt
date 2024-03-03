package es.architectcoders.spaceexplorer.framework.server.roverServer

import arrow.core.Either
import es.architectcoders.data.datasource.RoversRemoteDataSource
import es.architectcoders.domain.Error
import es.architectcoders.domain.Photo
import es.architectcoders.spaceexplorer.di.ApiKey
import es.architectcoders.spaceexplorer.framework.tryCall
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class RoversServerDataSource @Inject constructor(@ApiKey private val apiKey: String) :
    RoversRemoteDataSource {

    override suspend fun getRovers(date: Calendar): Either<Error, List<Photo>> = tryCall {
        RemoteConnection.service
            .getRovers(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date.time), apiKey)
            .photos
            .toDomain()
    }

    private fun List<PhotoResponse>.toDomain() : List<Photo> = map { it.toDomain() }

    private fun PhotoResponse.toDomain() : Photo =
        Photo(
            earthDate = earthDate,
            id = id,
            imgSrc = imgSrc.replace("http://", "https://"),
            sol = sol.toString(),
            favorite = false,
            type = "rover",
            title = "",
            url = "",
            serviceVersion = "",
            explanation = "",
            date = "",
            mediaType = "",
            hdurl = ""
        )
}