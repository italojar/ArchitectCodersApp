package es.architectcoders.domain.usecase

import es.architectcoders.data.repository.ApodRepository
import es.architectcoders.domain.mapper.toDomain
import es.architectcoders.domain.model.Apod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetApodsUseCase @Inject constructor(
    private val apodRepository: ApodRepository
) {
    operator fun invoke(): Flow<List<Apod>> {
        return apodRepository.getApods().map { apodList ->
            apodList.map { apodResponse -> apodResponse.toDomain() }
        }
    }
}