package es.architectcoders.spaceexplorer.framework.database.roverDb

import androidx.room.TypeConverter
import es.architectcoders.domain.Camera

class CameraConverter {
    @TypeConverter
    fun fromCamera(camera: Camera): String {
        return "${camera.fullName},${camera.id},${camera.name},${camera.roverId}"
    }

    @TypeConverter
    fun toCamera(cameraString: String): Camera {
        val parts = cameraString.split(",")
        return Camera(
            parts[0],
            parts[1].toInt(),
            parts[2],
            parts[3].toInt()
        )
    }
}