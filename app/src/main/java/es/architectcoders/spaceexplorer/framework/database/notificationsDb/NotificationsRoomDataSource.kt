package es.architectcoders.spaceexplorer.framework.database.notificationsDb

import android.util.Log
import es.architectcoders.data.datasource.NotificationsLocalDataSource
import es.architectcoders.domain.NotificationsItem
import es.architectcoders.domain.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import es.architectcoders.spaceexplorer.framework.tryCall
import javax.inject.Inject
import es.architectcoders.spaceexplorer.framework.database.notificationsDb.NotificationsItemEntity as DbNotificationsItem

class NotificationsRoomDataSource @Inject constructor(
    private val notificationsDao: NotificationsDao,
) : NotificationsLocalDataSource {

    override val getNotifications: Flow<List<NotificationsItem>> =
        notificationsDao.getAllNotifications().map {
            Log.d("GET LIST ROOM DATA SOURCE///////", it.toString())
            it.toDomain() }

    override suspend fun saveNotifications(notifications: List<NotificationsItem>): Error? = tryCall {
        notificationsDao.insertNotifications(
            notifications.fromDomain())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    override suspend fun isNotificationsEmpty(): Boolean = notificationsDao.notificationsCount() == 0

}
private fun List<DbNotificationsItem>.toDomain(): List<NotificationsItem> = map {
    Log.d("LIST ROOM DBnOTIFICATIONS TO DOMAIN DATA SOURCE///////", it.toString())
    it.toDomain() }


private fun DbNotificationsItem.toDomain(): NotificationsItem =
    NotificationsItem(
        messageType,
        messageID,
        messageURL,
        messageIssueTime,
        messageBody
    )

private fun List<NotificationsItem>.fromDomain(): List<DbNotificationsItem> = map { it.fromDomain() }

private fun NotificationsItem.fromDomain(): DbNotificationsItem =
    DbNotificationsItem(
        id = 0,
        messageType,
        messageID,
        messageURL,
        messageIssueTime,
        messageBody
    )
