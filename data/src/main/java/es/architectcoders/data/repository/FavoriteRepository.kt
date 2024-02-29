package es.architectcoders.data.repository

import es.architectcoders.data.datasource.ApodLocalDataSource
import es.architectcoders.data.datasource.RoversLocalDataSource
import es.architectcoders.domain.Apod
import es.architectcoders.domain.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val RoversLocalDataSource: RoversLocalDataSource,
    private val ApodLocalDataSource: ApodLocalDataSource
){

//    val allFavoritesRovers = RoversLocalDataSource.getFavoritePhoto
//    val allFavoritesApods = ApodLocalDataSource.getFavoriteApods
//
//    fun getFavoriteList() = merge(flowOf(allFavoritesApods, allFavoritesRovers))

    fun getFavoriteList(): Flow<List<Any>> {
        val allFavoritesApods: Flow<List<Apod>> = ApodLocalDataSource.getFavoriteApods
        val allFavoritesRovers: Flow<List<Photo>> = RoversLocalDataSource.getFavoritePhoto

        return merge(allFavoritesApods, allFavoritesRovers)
    }

}