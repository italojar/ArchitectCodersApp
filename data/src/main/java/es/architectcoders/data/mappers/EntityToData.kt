package es.architectcoders.data.mappers

import es.architectcoders.domain.model.Apod
import es.architectcoders.framework.database.entity.ApodEntity

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