package es.architectcoders.spaceexplorer.framework.server.roverServer

import arrow.core.Either
import es.architectcoders.data.datasource.RoversRemoteDataSource
import es.architectcoders.domain.Camera
import es.architectcoders.domain.CameraX
import es.architectcoders.domain.Error
import es.architectcoders.domain.Photo
import es.architectcoders.domain.Rover
import es.architectcoders.spaceexplorer.di.ApiKey
import es.architectcoders.spaceexplorer.framework.tryCall
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class RoversServerDataSource @Inject constructor(@ApiKey private val apiKey: String) :
    RoversRemoteDataSource {
//    override suspend fun getRovers(date: String, camera: String, page: Int, apiKey: String):
//            Either<Error, List<Photo>?> = tryCall {
//        apiClient.getRovers(date,apiKey)
//            .body()?.photos?.toDomain()
//    }

    override suspend fun getRovers(date: Calendar): Either<Error, List<Photo>> = tryCall {
        RemoteConnection.service
            .getRovers(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date.time), apiKey)
            .photos
            .toDomain()
    }

    private fun List<PhotoResponse>.toDomain() : List<Photo> = map { it.toDomain() }

    private fun PhotoResponse.toDomain() : Photo =
        Photo(
//            camera.toDomain(),
            earthDate,
            id,
            imgSrc.replace("http://", "https://"),
//            rover.toDomain(),
            sol.toString(),
            false
        )

    private fun CameraResponse.toDomain(): Camera =
        Camera(
            fullName,
            id,
            name,
            roverId
        )

    private fun RoverResponse.toDomain(): Rover =
        Rover(
            cameras.map { it.toDomain() },
            id,
            landingDate,
            launchDate,
            maxDate,
            maxSol,
            name,
            status,
            totalPhotos
        )

    private fun CameraXResponse.toDomain(): CameraX =
        CameraX(
            name,
            fullName
        )

    private fun Camera.toCameraResponse(): CameraResponse =
        CameraResponse(
            fullName,
            id,
            name,
            roverId
        )

    fun Rover.toRoverResponse(): RoverResponse =
        RoverResponse(
            cameras.map { it.toCameraXResponse() },
            id,
            landingDate,
            launchDate,
            maxDate,
            maxSol,
            name,
            status,
            totalPhotos
        )

    private fun CameraX.toCameraXResponse(): CameraXResponse =
        CameraXResponse(
            name,
            fullName
        )
}