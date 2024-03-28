package es.architectcoders.domain

data class NotificationsItem(
    val messageIssueTime: String,
    val messageID: String,
    val messageBody: String,
    val messageURL: String,
    val messageType: String
)