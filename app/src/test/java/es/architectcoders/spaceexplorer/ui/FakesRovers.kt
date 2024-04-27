package es.architectcoders.spaceexplorer.ui

import arrow.core.right
import es.architectcoders.data.datasource.RoversLocalDataSource
import es.architectcoders.data.datasource.RoversRemoteDataSource
import es.architectcoders.domain.Error
import es.architectcoders.domain.Photo
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Calendar

val samplePhoto = Photo(
    "2023-01-01",
    0,
    "https://example.com",
    "1",
    false
)

val defaultFakePhotos = listOf(
    samplePhoto.copy(id = 1),
    samplePhoto.copy(id = 2),
    samplePhoto.copy(id = 3),
    samplePhoto.copy(id = 4)
)

class FakeRoversLocalDataSource : RoversLocalDataSource {

    val inMemoryPhotos = MutableStateFlow<List<Photo>>(emptyList())

    override val getPhoto: MutableStateFlow<List<Photo>>
        get() = inMemoryPhotos

    override suspend fun saveRovers(rovers: List<Photo>): Error? {
        inMemoryPhotos.value = rovers
        return null
    }

    override suspend fun isRoversEmpty() = getPhoto.value.isEmpty()
}

class FakeRoversRemoteDataSource : RoversRemoteDataSource {

    var photos = defaultFakePhotos

    override suspend fun getRovers(date: Calendar) = photos.right()
}

