package es.architectcoders.domain

interface NasaItem {
    var id: String
    val date: String
    val title: String?
    val explanation: String?
    val hdurl: String?
    val url: String?
    val mediaType: String?
    val serviceVersion: String
    var favorite: Boolean
    val type: String
}