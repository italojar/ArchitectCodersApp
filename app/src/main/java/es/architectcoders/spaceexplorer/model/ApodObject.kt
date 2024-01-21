package es.architectcoders.spaceexplorer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApodObject(
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
): Parcelable {
    constructor() : this(0, "", "", "",
        "", "", "", "", "", false)
}