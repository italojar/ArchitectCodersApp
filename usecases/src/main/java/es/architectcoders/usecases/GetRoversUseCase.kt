package es.architectcoders.usecases

import es.architectcoders.data.repository.RoversRepository
import javax.inject.Inject

class GetRoversUseCase @Inject constructor(
    private val roversRepository: RoversRepository
){
    operator fun invoke() = roversRepository.allRovers

}