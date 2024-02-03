package es.architectcoders.spaceexplorer.framework.server

import com.google.gson.annotations.SerializedName

//Todo : Coger la minima informacion necesaria
data class RoverResponse(
    val cameras: List<CameraXResponse>,
    val id: Int,
    @SerializedName("landing_date") val landingDate: String,
    @SerializedName("launch_date") val launchDate: String,
    @SerializedName("max_date") val maxDate: String,
    @SerializedName("max_sol") val maxSol: Int,
    val name: String,
    val status: String,
    @SerializedName("total_photos") val totalPhotos: Int
)