package es.architectcoders.data.datasource

import arrow.core.Either
import es.architectcoders.domain.Apod
import es.architectcoders.domain.Error


interface ApodRemoteDataSource {
    suspend fun getApod(): Either<Error, Apod?>
}