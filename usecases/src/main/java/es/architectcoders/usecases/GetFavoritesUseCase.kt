package es.architectcoders.usecases

import es.architectcoders.data.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
){
    operator fun invoke() = favoriteRepository.getFavoriteList()
}