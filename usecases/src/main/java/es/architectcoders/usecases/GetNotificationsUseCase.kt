package es.architectcoders.usecases

import es.architectcoders.data.repository.NotificationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotificationsUseCase @Inject constructor(
    private val notificationsRepository: NotificationsRepository
) {

    operator fun invoke() = notificationsRepository.allNotifications
}