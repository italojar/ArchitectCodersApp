package es.architectcoders.domain.mapper

import es.architectcoders.data.source.local.model.ApodData
import es.architectcoders.domain.model.Apod

fun Apod.toData() = ApodData(
    id = id,
    copyright = copyright,
    date = date,
    explanation = explanation,
    hdurl = hdurl ,
    mediaType = mediaType,
    serviceVersion = serviceVersion,
    title = title,
    url = url,
    favorite = favorite
)