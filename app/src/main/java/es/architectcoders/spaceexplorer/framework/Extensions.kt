package es.architectcoders.spaceexplorer.framework

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import es.architectcoders.domain.Apod
import es.architectcoders.domain.Error
import es.architectcoders.domain.Photo
import es.architectcoders.spaceexplorer.framework.database.apodDb.ApodEntity
import es.architectcoders.spaceexplorer.framework.database.roverDb.PhotoEntity
import es.architectcoders.spaceexplorer.framework.server.apodServer.ApodResponse
import retrofit2.HttpException
import java.io.IOException

fun Apod.toEntity() = ApodEntity(
    id = id,
    copyright = copyright,
    date = date,
    explanation = explanation,
    hdurl = hdurl,
    mediaType = mediaType,
    serviceVersion = serviceVersion,
    title = title,
    url = url,
    favorite = favorite
)

fun ApodEntity.toDomain() = Apod(
    id = this.id,
    copyright = this.copyright,
    date = this.date,
    explanation = this.explanation,
    hdurl = this.hdurl,
    mediaType = this.mediaType,
    serviceVersion = this.serviceVersion,
    title = this.title,
    url = this.url,
    favorite = this.favorite
)

fun ApodResponse.toEntity() = ApodEntity(
    copyright = this.copyright ?: "",
    date = this.date,
    explanation = this.explanation,
    hdurl = this.hdurl,
    mediaType = this.mediaType,
    serviceVersion = this.serviceVersion,
    title = this.title,
    url = this.url,
    favorite = false
)

fun ApodResponse.toDomain() = Apod(
    id = this.id,
    copyright = this.copyright ?: "",
    date = this.date,
    explanation = this.explanation,
    hdurl = this.hdurl,
    mediaType = this.mediaType,
    serviceVersion = this.serviceVersion,
    title = this.title,
    url = this.url,
    favorite = false
)

//fun List<Photo>.toEntity() : List<PhotoEntity> {
//    return this.map {
//        it.toEntity()
//    }
//}
//
///**
// * Map from Photo DOMAIN to Photo Database
// */
//fun Photo.toEntity() = PhotoEntity(
//    camera = this.camera,
//    earthDate = this.earthDate,
//    id = this.id,
//    imgSrc = this.imgSrc,
//    rover = this.rover,
//    sol = this.sol,
//    favorite = this.favorite
//)
//
//fun PhotoEntity.toDomain() = Photo(
//    camera = this.camera,
//    earthDate = this.earthDate,
//    id = this.id,
//    imgSrc = this.imgSrc,
//    rover = this.rover,
//    sol = this.sol,
//    favorite = this.favorite
//)





fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}

suspend fun <T> tryCall(action: suspend () -> T): Either<Error, T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}