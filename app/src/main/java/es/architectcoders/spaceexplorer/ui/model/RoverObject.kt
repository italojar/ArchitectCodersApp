package es.architectcoders.spaceexplorer.ui.model

import es.architectcoders.domain.CameraX

data class RoverObject(
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