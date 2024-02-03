package es.architectcoders.usecases

import es.architectcoders.data.repository.RoversRepository
import es.architectcoders.domain.Error
import es.architectcoders.domain.Photo
import javax.inject.Inject

class SaveRoversFavoriteUseCase @Inject constructor(
    private val roversRepository: RoversRepository
){
    suspend operator fun invoke(photo: Photo): Error? {
        return roversRepository.saveRoversAsFavourite(photo)
    }
}