package es.architectcoders.spaceexplorer.framework.server.notificationsServer

import com.google.gson.annotations.SerializedName

data class NotificationsItemResponse(
    val messageType: String,
    val messageID: String,
    val messageURL: String,
    val messageIssueTime: String,
    val messageBody: String
)