package es.architectcoders.spaceexplorer.ui.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import es.architectcoders.spaceexplorer.framework.server.roverServer.CameraResponse
import es.architectcoders.spaceexplorer.framework.server.roverServer.RoverResponse
import kotlinx.parcelize.RawValue

@Parcelize
data class PhotoObject(
    val camera: @RawValue CameraResponse,
    val earthDate: String,
    val id: Int,
    val imgSrc: String,
    val rover: @RawValue RoverResponse,
    val sol: Int,
    val favorite: Boolean
): Parcelable
