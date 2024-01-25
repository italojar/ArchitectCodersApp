package es.architectcoders.data.mappers

import es.architectcoders.framework.database.entity.ApodEntity
import es.architectcoders.framework.model.ApodResponse

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