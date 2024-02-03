package es.architectcoders.domain

data class Rover(
    // Todo: Estudiar como sustituir CameraX por atributos primitivos
    val cameras: List<CameraX>,
    val id: Int,
    val landingDate: String,
    val launchDate: String,
    val maxDate: String,
    val maxSol: Int,
    val name: String,
    val status: String,
    val totalPhotos: Int
)