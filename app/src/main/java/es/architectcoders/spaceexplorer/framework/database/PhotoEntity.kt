package es.architectcoders.spaceexplorer.framework.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.architectcoders.domain.Camera
import es.architectcoders.domain.Rover
import es.architectcoders.spaceexplorer.framework.server.CameraResponse
import es.architectcoders.spaceexplorer.framework.server.RoverResponse

@Entity
data class PhotoEntity(
    // Todo: Estudiar como sustituir Camera por atributos primitivos
    val camera: Camera,
    val earthDate: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imgSrc: String,
    // Todo: Estudiar como sustituir Rover por atributos primitivos
    val rover: Rover,
    val sol: Int,
    val favorite: Boolean
)
