package es.architectcoders.spaceexplorer.framework.database.notificationsDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotificationsItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,//included for me
    val messageIssueTime: String,
    val messageID: String,
    val messageBody: String,
    val messageURL: String,
    val messageType: String
)
