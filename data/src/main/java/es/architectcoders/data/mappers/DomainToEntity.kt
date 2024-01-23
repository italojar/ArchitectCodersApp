package es.architectcoders.data.mappers

import es.architectcoders.data.database.entity.ApodEntity
import es.architectcoders.domain.model.Apod

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