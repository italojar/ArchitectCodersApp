package es.architectcoders.data.datasource

import es.architectcoders.domain.NotificationsItem
import kotlinx.coroutines.flow.Flow
import es.architectcoders.domain.Error

interface NotificationsLocalDataSource {

    val getNotifications: Flow<List<NotificationsItem>>

    suspend fun saveNotifications(notifications: List<NotificationsItem>): Error?

    suspend fun isNotificationsEmpty(): Boolean
}