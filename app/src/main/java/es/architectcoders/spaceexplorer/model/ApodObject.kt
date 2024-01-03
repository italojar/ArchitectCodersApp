package es.architectcoders.spaceexplorer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApodObject(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val mediaType: String,
    val serviceVersion: String,
    val title: String,
    val url: String
): Parcelable {
    constructor() : this("", "", "",
        "", "", "", "", "")
}