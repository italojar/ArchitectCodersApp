package es.architectcoders.data.datasource


import es.architectcoders.domain.Apod
import es.architectcoders.domain.Error
import kotlinx.coroutines.flow.Flow

interface ApodLocalDataSource {
    suspend fun saveApod(apod: Apod?): Error?

    val getApods: Flow<List<Apod>>

    val getFavoriteApods: Flow<List<Apod>>

    suspend fun isApodEmpty(): Boolean

    suspend fun apodExists(apod: Apod?): Boolean

    suspend fun saveApodAsFavourite(apod: Apod?): Error?
}