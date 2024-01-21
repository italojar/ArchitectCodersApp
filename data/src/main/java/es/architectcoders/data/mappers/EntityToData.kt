package es.architectcoders.data.mappers

import es.architectcoders.data.database.entity.ApodEntity
import es.architectcoders.data.source.local.model.ApodData
import es.architectcoders.data.source.network.model.ApodResponse

fun ApodEntity.toData() = ApodData(
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