package es.architectcoders.data.source.local.model

data class ApodData(
    val id: Int,
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val mediaType: String,
    val serviceVersion: String,
    val title: String,
    val url: String,
    val favorite: Boolean
)
