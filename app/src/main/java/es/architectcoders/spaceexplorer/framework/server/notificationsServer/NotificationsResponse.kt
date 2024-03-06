package es.architectcoders.spaceexplorer.framework.server.notificationsServer

data class NotificationsItemResponse(
    val messageIssueTime: String,
    val messageID: String,
    val messageBody: String,
    val messageURL: String,
    val messageType: String
)