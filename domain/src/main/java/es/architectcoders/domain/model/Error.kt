package es.architectcoders.domain.model

sealed interface Error {
    class Server(val code: Int, val message: String) : Error
    data object Connectivity : Error
    class Unknown(val message: String) : Error
}

fun Error.toError(): Error {
    return when (this) {
        is Error.Server -> Error.Server(this.code, this.message)
        is Error.Connectivity -> Error.Connectivity
        is Error.Unknown -> Error.Unknown(this.message)
    }
}