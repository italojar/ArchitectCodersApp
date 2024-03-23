package es.architectcoders.data.repository

import arrow.core.left
import arrow.core.right
import es.architectcoders.data.datasource.ApodLocalDataSource
import es.architectcoders.data.datasource.ApodRemoteDataSource
import es.architectcoders.domain.Apod
import es.architectcoders.domain.Error
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ApodRepositoryTest {

    @RelaxedMockK
    private lateinit var localDataSource: ApodLocalDataSource
    @RelaxedMockK
    private lateinit var remoteDataSource: ApodRemoteDataSource

    private lateinit var repository: ApodRepository

    @Before
    fun onBefore()  {
        MockKAnnotations.init(this)
        repository = ApodRepository(
            apodLocalDataSource = localDataSource,
            apodRemoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `when requestApod response is error should return error`(): Unit = runBlocking {
        // Given
        coEvery { remoteDataSource.getApod() } returns Error.Connectivity.left()

        // When
        val getApod = repository.requestApod()

        // Then
        coVerify(exactly = 1) {  remoteDataSource.getApod() }
        coVerify(exactly = 0) { localDataSource.apodExists(any()) }
        coVerify(exactly = 0) { localDataSource.saveApod(any()) }
        assert( getApod == Error.Connectivity )
    }

    @Test
    fun `when requestApod response is Apod and is not exists should call saveApod and return null`(): Unit =
        runBlocking {
            // Given
            val apod = Apod(
                1, "copyright", "date",
                "explanation", "hdurl", "mediaType",
                "serviceVersion", "title", "url", false
            )
            coEvery { remoteDataSource.getApod() } returns apod.right()
            coEvery { localDataSource.apodExists(any()) } returns false

            // When
            val getApod = repository.requestApod()

            // Then
            coVerify(exactly = 1) { remoteDataSource.getApod() }
            coVerify(exactly = 1) { localDataSource.apodExists(any()) }
            coVerify(exactly = 1) { localDataSource.saveApod(any()) }
            assert(getApod == null)
        }

    @Test
    fun `when requestApod response is Apod and exists shouldn't call saveApod and return null`(): Unit = runBlocking {
        // Given
        val apod = Apod(1, "copyright", "date",
            "explanation", "hdurl", "mediaType",
            "serviceVersion", "title", "url", false
        )
        coEvery { remoteDataSource.getApod() } returns apod.right()
        coEvery {  localDataSource.apodExists(any()) } returns true

        // When
        val getApod = repository.requestApod()

        // Then
        coVerify(exactly = 1) {  remoteDataSource.getApod() }
        coVerify(exactly = 1) { localDataSource.apodExists(any()) }
        coVerify(exactly = 0) { localDataSource.saveApod(any()) }
        assert( getApod == null )
    }
}