package es.architectcoders.spaceexplorer.framework.database.apodDb

import es.architectcoders.domain.Apod
import es.architectcoders.domain.Error
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ApodRoomDataSourceTest {

    @RelaxedMockK
    private lateinit var apodDao: ApodDao

    private lateinit var apodRoomDataSource: ApodRoomDataSource

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        apodRoomDataSource = ApodRoomDataSource(apodDao)
    }

    @Test
    fun `when calls saveApod if apod argument is not null insert in db`() : Unit = runBlocking {
        // Given
        val apod = Apod(
            1, "copyright", "date",
            "explanation", "hdurl", "mediaType",
            "serviceVersion", "title", "url", false
        )
        coEvery { apodRoomDataSource.saveApod(apod) } returns null

        // When
        val saveApod = apodRoomDataSource.saveApod(apod)

        // Then
        coVerify(exactly = 1) { apodDao.insertApod(any()) }
        assert(saveApod == null)
    }

    @Test
    fun `when calls saveApod if apod argument is  null not insert in db and catch error`() : Unit = runBlocking {
        // Given
        coEvery { apodRoomDataSource.saveApod(any()) } returns Error.Unknown("Error")

        // When
        val saveApod = apodRoomDataSource.saveApod(null)

        // Then
        coVerify(exactly = 0) { apodDao.insertApod(any()) }
        assert(saveApod == Error.Unknown("Error"))
    }
}