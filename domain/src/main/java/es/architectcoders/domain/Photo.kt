package es.architectcoders.domain

data class Photo(
    val earthDate: String,
    val id: String,
    val imgSrc: String,
    val sol: String,
    val favorite: Boolean
)
