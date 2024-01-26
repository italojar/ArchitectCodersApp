package es.architectcoders.usecases

import es.architectcoders.data.repository.ApodRepository
import es.architectcoders.domain.Apod
import es.architectcoders.domain.Error
import javax.inject.Inject

class SaveApodFavoriteUseCase @Inject constructor(
    private val apodRepository: ApodRepository
) {
    suspend operator fun invoke(apod: Apod): Error? {
        return apodRepository.saveApodAsFavourite(apod)
    }
}