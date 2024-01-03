package es.architectcoders.spaceexplorer.common

import es.architectcoders.domain.model.Apod
import es.architectcoders.spaceexplorer.model.ApodObject

fun Apod.toViewObject() = ApodObject(
    copyright = copyright,
    date = date,
    explanation = explanation,
    hdurl = hdurl,
    mediaType = mediaType,
    serviceVersion = serviceVersion,
    title = title,
    url = url
)