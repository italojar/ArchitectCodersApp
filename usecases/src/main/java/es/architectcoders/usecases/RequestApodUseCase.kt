package es.architectcoders.usecases

import es.architectcoders.data.repository.ApodRepository
import es.architectcoders.domain.Error
import javax.inject.Inject

class RequestApodUseCase @Inject constructor(
    private val apodRepository: ApodRepository
) {
    suspend operator fun invoke(): Error? {
        return apodRepository.requestApod()
    }
}