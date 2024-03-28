package es.architectcoders.spaceexplorer.framework.server.apodServer

import com.google.gson.annotations.SerializedName

data class ApodResponse(
    val id: Int,
    val copyright: String? = null,
    val date: String,
    val explanation: String,
    val hdurl: String? = null,
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("service_version") val serviceVersion: String,
    val title: String,
    val url: String
)