package es.architectcoders.spaceexplorer.framework.server

import com.google.gson.annotations.SerializedName

data class CameraXResponse(
    @SerializedName("full_name") val fullName: String,
    val name: String
)