package es.architectcoders.spaceexplorer.framework.server

import com.google.gson.annotations.SerializedName


data class CameraResponse(
    @SerializedName("full_name") val fullName: String,
    val id: Int,
    val name: String,
    @SerializedName("rover_id") val roverId: Int
)