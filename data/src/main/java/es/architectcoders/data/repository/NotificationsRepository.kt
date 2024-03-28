package es.architectcoders.data.repository

import es.architectcoders.data.datasource.NotificationsLocalDataSource
import es.architectcoders.data.datasource.NotificationsRemoteDataSource
import javax.inject.Inject
import es.architectcoders.domain.Error
import java.util.Calendar

class NotificationsRepository @Inject constructor(
    private val notificationsLocalDataSource: NotificationsLocalDataSource,
    private val notificationsRemoteDataSource: NotificationsRemoteDataSource
)  {
    val allNotifications get() = notificationsLocalDataSource.getNotifications

    suspend fun requestNotifications(): Error? {
        if (notificationsLocalDataSource.isNotificationsEmpty()){
            val notifications = notificationsRemoteDataSource.getNotifications(
                Calendar.getInstance().apply {
                    set(Calendar.DAY_OF_MONTH,
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1)
                }
            )
            notifications.fold(ifLeft = {
                return it }) {
                notificationsLocalDataSource.saveNotifications(it)
            }
        }
        return null
    }
}