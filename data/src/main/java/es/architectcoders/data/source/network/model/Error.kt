package es.architectcoders.data.source.network.model

import java.io.IOException

sealed interface Error {
    class Server(val code: Int, val message: String) : Error
    data object Connectivity : Error
    class Unknown(val message: String) : Error
}

fun Throwable.toError(): es.architectcoders.domain.model.Error {
    return when (this) {
        is retrofit2.HttpException -> es.architectcoders.domain.model.Error.Server(code(), message())
        is IOException -> es.architectcoders.domain.model.Error.Connectivity
        else -> es.architectcoders.domain.model.Error.Unknown(message ?: "Unknown error")
    }
}