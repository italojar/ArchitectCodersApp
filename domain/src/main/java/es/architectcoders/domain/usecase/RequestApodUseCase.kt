package es.architectcoders.domain.usecase

import es.architectcoders.data.repository.ApodRepository
import es.architectcoders.domain.model.Error
import es.architectcoders.domain.model.toError
import javax.inject.Inject

class RequestApodUseCase @Inject constructor(
    private val apodRepository: ApodRepository
) {
    suspend operator fun invoke(): Error? {
        val apodRequest = apodRepository.requestApod()
        return apodRequest?.toError()
    }
}