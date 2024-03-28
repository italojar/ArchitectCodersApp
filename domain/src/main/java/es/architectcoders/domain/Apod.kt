package es.architectcoders.domain

data class Apod(
    override var id: String,
    val copyright: String,
    override val date: String,
    override val explanation: String,
    override val hdurl: String,
    override val mediaType: String,
    override val serviceVersion: String,
    override val title: String,
    override val url: String,
    override var favorite: Boolean = false,
    override val type: String = "apod"
) : NasaItem
