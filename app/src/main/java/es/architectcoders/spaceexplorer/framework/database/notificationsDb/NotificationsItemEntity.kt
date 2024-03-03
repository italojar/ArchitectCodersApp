package es.architectcoders.spaceexplorer.framework.database.notificationsDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotificationsItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,//included for me
    val messageType: String,
    val messageID: String,
    val messageURL: String,
    val messageIssueTime: String,
    val messageBody: String
)
