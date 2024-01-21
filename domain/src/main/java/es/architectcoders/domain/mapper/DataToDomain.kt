package es.architectcoders.domain.mapper

import es.architectcoders.data.source.local.model.ApodData
import es.architectcoders.data.source.network.model.ApodResponse
import es.architectcoders.domain.model.Apod

fun ApodData.toDomain() = Apod(
    id = id,
    copyright = copyright.replace("\n", " "),
    date = date,
    explanation = explanation,
    hdurl = hdurl,
    mediaType = mediaType,
    serviceVersion = serviceVersion,
    title = title,
    url = url,
    favorite = favorite
)