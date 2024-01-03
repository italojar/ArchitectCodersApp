package es.architectcoders.domain.model

data class Apod(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val mediaType: String,
    val serviceVersion: String,
    val title: String,
    val url: String
) {
    constructor() : this("", "", "",
        "", "", "", "", "")
}
