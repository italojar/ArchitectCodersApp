package es.architectcoders.domain.mapper

import es.architectcoders.data.source.network.model.ApodResponse
import es.architectcoders.domain.model.Apod

fun ApodResponse.toDomain() = Apod(
    copyright = copyright.replace("\n", " "),
    date = date,
    explanation = explanation,
    hdurl = hdurl,
    mediaType = mediaType,
    serviceVersion = serviceVersion,
    title = title,
    url = url
)