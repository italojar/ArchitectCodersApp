package es.architectcoders.domain

data class Photo(
    override val date: String,
    override val title: String?,
    override val explanation: String?,
    override val hdurl: String?,
    override val url: String?,
    override val mediaType: String?,
    override val serviceVersion: String,
    override val type: String,
    override var favorite: Boolean,
    val sol: String,
    val imgSrc: String,
    override var id: Int,
    val earthDate: String
) : NasaItem
