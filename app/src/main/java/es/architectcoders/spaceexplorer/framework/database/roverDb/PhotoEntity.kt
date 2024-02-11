package es.architectcoders.spaceexplorer.framework.database.roverDb

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import es.architectcoders.domain.Camera
import es.architectcoders.domain.Rover

@Entity
data class PhotoEntity(
    // Todo: Estudiar como sustituir Camera por atributos primitivos
    @field:TypeConverters(CameraConverter::class) val camera: Camera,
    val earthDate: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imgSrc: String,
    // Todo: Estudiar como sustituir Rover por atributos primitivos
    @field:TypeConverters(RoverConverter::class) val rover: Rover,
    val sol: Int,
    val favorite: Boolean
)
