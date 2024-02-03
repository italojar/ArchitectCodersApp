package es.architectcoders.usecases

import es.architectcoders.data.repository.RoversRepository
import es.architectcoders.domain.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoversUseCase @Inject constructor(
    private val roversRepository: RoversRepository
){
    operator fun invoke(): Flow<List<Photo>> {
        return roversRepository.allRovers
    }
}