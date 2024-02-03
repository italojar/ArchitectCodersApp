package es.architectcoders.spaceexplorer.framework.server

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    val camera: CameraResponse,
    @SerializedName("earth_date") val earthDate: String,
    val id: Int,
    @SerializedName("img_src") val imgSrc: String,
    val rover: RoverResponse,
    val sol: Int
)