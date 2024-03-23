package es.architectcoders.spaceexplorer.framework.server.notificationsServer

import arrow.core.Either
import es.architectcoders.data.datasource.NotificationsRemoteDataSource
import es.architectcoders.domain.Error
import es.architectcoders.domain.NotificationsItem
import es.architectcoders.spaceexplorer.di.ApiKey
import es.architectcoders.spaceexplorer.framework.tryCall
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class NotificationsServerDataSource @Inject constructor(@ApiKey private val apiKey: String) :
    NotificationsRemoteDataSource {


    override suspend fun getNotifications(date: Calendar): Either<Error, List<NotificationsItem>> =
        tryCall {

            val dateLessSevenDays = date.clone() as Calendar

            dateLessSevenDays.add(Calendar.DAY_OF_YEAR, -7)

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val currentDateFormat = dateFormat.format(date.time)

            val dateLessSevenDaysFormat = dateFormat.format(dateLessSevenDays.time)

            RemoteNotificationsConnection.service
                .getNotifications(
                    dateLessSevenDaysFormat,
                    currentDateFormat,
                    "all",
                    apiKey
                )
                .toDomain()
        }

    private fun List<NotificationsItemResponse>.toDomain(): List<NotificationsItem> =
        map {
            it.toDomain()
        }

    private fun NotificationsItemResponse.toDomain(): NotificationsItem =
        NotificationsItem(
            messageIssueTime,
            messageID,
            messageBody,
            messageURL,
            messageType
        )
}