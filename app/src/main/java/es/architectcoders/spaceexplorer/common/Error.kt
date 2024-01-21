package es.architectcoders.spaceexplorer.common

sealed interface Error {
    class Server(val code: Int, val message: String) : Error
    data object Connectivity : Error
    class Unknown(val message: String) : Error
}

fun es.architectcoders.domain.model.Error.toError(): Error {
    return when (this) {
        is es.architectcoders.domain.model.Error.Server -> Error.Server(this.code, this.message)
        is es.architectcoders.domain.model.Error.Connectivity -> Error.Connectivity
        is es.architectcoders.domain.model.Error.Unknown -> Error.Unknown(this.message)
    }
}