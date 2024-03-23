package es.architectcoders.spaceexplorer.framework.database.roverDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoEntity(
    val earthDate: String,
    @PrimaryKey(autoGenerate = true) val id: Int,
    val imgSrc: String,
    val sol: String,
    val favorite: Boolean
)
