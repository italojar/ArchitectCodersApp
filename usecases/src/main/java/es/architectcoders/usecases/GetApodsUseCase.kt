package es.architectcoders.usecases

import es.architectcoders.data.repository.ApodRepository
import es.architectcoders.domain.model.Apod
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetApodsUseCase @Inject constructor(
    private val apodRepository: ApodRepository
) {
    operator fun invoke(): Flow<List<Apod>> {
        return apodRepository.getApods()
    }
}