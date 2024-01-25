package es.architectcoders.framework.model

import java.io.IOException

sealed interface Error {
    class Server(val code: Int, val message: String) : Error
    data object Connectivity : Error
    class Unknown(val message: String) : Error
}

fun Throwable.toError(): Error {
    return when (this) {
        is retrofit2.HttpException -> Error.Server(code(), message())
        is IOException -> Error.Connectivity
        else -> Error.Unknown(message ?: "Unknown error")
    }
}