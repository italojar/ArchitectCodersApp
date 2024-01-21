package es.architectcoders.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ApodEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val mediaType: String,
    val serviceVersion: String,
    val title: String,
    val url: String,
    val favorite: Boolean
)
