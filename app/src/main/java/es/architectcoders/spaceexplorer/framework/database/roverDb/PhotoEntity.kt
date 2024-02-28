package es.architectcoders.spaceexplorer.framework.database.roverDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoEntity(
//    @field:TypeConverters(CameraConverter::class) val camera: Camera,
    val earthDate: String,
    @PrimaryKey(autoGenerate = true) val id: Int,
    val imgSrc: String,
//    @field:TypeConverters(RoverConverter::class) val rover: Rover,
    val sol: String,
    val favorite: Boolean
)
