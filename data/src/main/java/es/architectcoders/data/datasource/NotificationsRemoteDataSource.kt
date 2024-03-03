package es.architectcoders.data.datasource

import arrow.core.Either
import es.architectcoders.domain.NotificationsItem
import es.architectcoders.domain.Error
import java.util.Calendar

interface NotificationsRemoteDataSource {

    suspend fun getNotifications(date: Calendar): Either<Error, List<NotificationsItem>>
}