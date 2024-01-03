package es.architectcoders.domain.usecase

import es.architectcoders.data.repository.ApodRepository
import es.architectcoders.domain.mapper.toDomain
import es.architectcoders.domain.model.Apod
import javax.inject.Inject

class GetApodUseCase @Inject constructor(
    private val apodRepository: ApodRepository
) {
    suspend operator fun invoke(): Apod = apodRepository.getApod()?.toDomain() ?: Apod()
}