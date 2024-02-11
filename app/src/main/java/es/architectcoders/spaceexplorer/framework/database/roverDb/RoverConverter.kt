package es.architectcoders.spaceexplorer.framework.database.roverDb

import androidx.room.TypeConverter
import es.architectcoders.domain.Rover

class RoverConverter {

    @TypeConverter
    fun fromRover(rover: Rover): String = rover.name

    @TypeConverter
    fun toRover(value: String): Rover =
        Rover(
            name = value,
            id = 0,
            cameras = emptyList(),
            totalPhotos = 0,
            landingDate = "",
            launchDate = "",
            maxDate = "",
            maxSol = 0,
            status = ""
        )
}