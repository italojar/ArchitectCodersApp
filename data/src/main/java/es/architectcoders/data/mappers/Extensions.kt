package es.architectcoders.data.mappers

import es.architectcoders.domain.model.Apod
import es.architectcoders.framework.database.entity.ApodEntity
import es.architectcoders.framework.model.ApodResponse
import es.architectcoders.framework.model.Error

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
    copyright = this.copyright,
    date = this.date,
    explanation = this.explanation,
    hdurl = this.hdurl,
    mediaType = this.mediaType,
    serviceVersion = this.serviceVersion,
    title = this.title,
    url = this.url,
    favorite = false
)

fun Error.toDomain(): es.architectcoders.domain.model.Error {
    return when (this) {
        is Error.Server -> es.architectcoders.domain.model.Error.Server(this.code, this.message)
        is Error.Connectivity -> es.architectcoders.domain.model.Error.Connectivity
        is Error.Unknown -> es.architectcoders.domain.model.Error.Unknown(this.message)
    }
}