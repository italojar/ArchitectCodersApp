package es.architectcoders.domain.usecase

import es.architectcoders.data.repository.ApodRepository
import es.architectcoders.domain.mapper.toData
import es.architectcoders.domain.model.Apod
import es.architectcoders.domain.model.Error
import es.architectcoders.domain.model.toError
import javax.inject.Inject

class SaveApodFavoriteUseCase @Inject constructor(
    private val apodRepository: ApodRepository
) {
    suspend operator fun invoke(apod: Apod): Error? {
        val apodRequest = apodRepository.saveApodAsFavourite(apod.toData())
        return apodRequest?.toError()
    }
}