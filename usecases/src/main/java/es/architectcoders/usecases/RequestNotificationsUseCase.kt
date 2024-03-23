package es.architectcoders.usecases

import es.architectcoders.data.repository.NotificationsRepository
import es.architectcoders.domain.Error
import javax.inject.Inject

class RequestNotificationsUseCase @Inject constructor(
    private val notificationsRepository: NotificationsRepository
){
    suspend operator fun invoke(): Error? {
        return notificationsRepository.requestNotifications()
    }
}