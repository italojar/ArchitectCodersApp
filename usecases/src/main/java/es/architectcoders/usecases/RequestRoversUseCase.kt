package es.architectcoders.usecases

import es.architectcoders.data.repository.RoversRepository
import es.architectcoders.domain.Error
import javax.inject.Inject

class RequestRoversUseCase @Inject constructor(
    private val roversRepository: RoversRepository
){
    suspend operator fun invoke(date: String): Error? {
        return roversRepository.requestRovers(date)
    }
}