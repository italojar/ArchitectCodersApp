package es.architectcoders.usecases

import es.architectcoders.data.repository.RoversRepository
import es.architectcoders.domain.Error
import javax.inject.Inject

class RequestRoversUseCase @Inject constructor(
    private val roversRepository: RoversRepository
){
    suspend operator fun invoke(date: String, camera: String, page: Int, apiKey: String): Error? {
        return roversRepository.requestRovers(date, camera, page, apiKey)
    }
}