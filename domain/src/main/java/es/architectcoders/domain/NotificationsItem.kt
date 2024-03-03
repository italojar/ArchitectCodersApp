package es.architectcoders.domain

data class NotificationsItem(
    val messageBody: String,
    val messageID: String,
    val messageIssueTime: String,
    val messageType: String,
    val messageURL: String
)