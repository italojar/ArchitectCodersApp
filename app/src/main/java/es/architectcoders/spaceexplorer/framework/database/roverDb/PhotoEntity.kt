package es.architectcoders.spaceexplorer.framework.database.roverDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoEntity(
    val earthDate: String,
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: String,
    val title: String?,
    val explanation: String?,
    val hdurl: String?,
    val url: String?,
    val mediaType: String?,
    val serviceVersion: String,
    val type: String,
    var favorite: Boolean,
    val sol: String,
    val imgSrc: String
)
