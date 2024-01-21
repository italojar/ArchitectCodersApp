package es.architectcoders.domain.model

import java.io.IOException

sealed interface Error {
    class Server(val code: Int, val message: String) : Error
    data object Connectivity : Error
    class Unknown(val message: String) : Error
}

fun es.architectcoders.data.source.network.model.Error.toError(): Error {
    return when (this) {
        is es.architectcoders.data.source.network.model.Error.Server -> Error.Server(this.code, this.message)
        is es.architectcoders.data.source.network.model.Error.Connectivity -> Error.Connectivity
        is es.architectcoders.data.source.network.model.Error.Unknown -> Error.Unknown(this.message)
    }
}