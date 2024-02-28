package es.architectcoders.spaceexplorer.framework.database.roverDb

import androidx.room.TypeConverter
import es.architectcoders.domain.CameraX
import es.architectcoders.domain.Rover

class RoverConverter {

    @TypeConverter
    fun fromRover(rover: Rover): String {
        val camerasString = rover.cameras.joinToString(";") { "${it.fullName},${it.name}" }
        return "${rover.id}," +
                "${rover.landingDate}," +
                "${rover.launchDate}," +
                "${rover.maxDate}," +
                "${rover.maxSol}," +
                "${rover.name}," +
                "${rover.status}," +
                "${rover.totalPhotos}," +
                camerasString
    }

    @TypeConverter
    fun toRover(roverString: String): Rover {
        val parts = roverString.split(",")
        val camerasList = parts.subList(9, parts.size).map {
            val cameraParts = it.split(";")
            CameraX(cameraParts[0], cameraParts[1])
        }
        return Rover(
            camerasList,
            parts[0].toInt(),
            parts[1],
            parts[2],
            parts[3],
            parts[4].toInt(),
            parts[5],
            parts[6],
            parts[7].toInt()
        )
    }
}