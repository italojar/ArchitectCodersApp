package es.architectcoders.spaceexplorer.framework.database.roverDb

import androidx.room.TypeConverter
import es.architectcoders.domain.Camera

class CameraConverter {
    @TypeConverter
    fun fromCamera(camera: Camera): String = camera.name

    @TypeConverter
    fun toCamera(value: String): Camera =
        Camera(
            name = value,
            id = 0,
            fullName = "",
            roverId = 0
        )
}