package es.architectcoders.spaceexplorer.framework.server

import com.google.gson.annotations.SerializedName

data class ApodResponse(
    val id: Int,
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("service_version") val serviceVersion: String,
    val title: String,
    val url: String
)