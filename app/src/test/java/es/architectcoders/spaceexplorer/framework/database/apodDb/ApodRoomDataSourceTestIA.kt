package es.architectcoders.spaceexplorer.framework.database.apodDb

import es.architectcoders.domain.Apod
import es.architectcoders.spaceexplorer.framework.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.wheneverBlocking

@RunWith(MockitoJUnitRunner::class)
class ApodRoomDataSourceTestIA {

    @Mock
    lateinit var apodDao: ApodDao

    private lateinit var apodRoomDataSource: ApodRoomDataSource

    @Before
    fun onBefore() {
        apodRoomDataSource = ApodRoomDataSource(apodDao)
    }

    private val apod = Apod(
        1, "copyright", "date",
        "explanation", "hdurl", "mediaType",
        "serviceVersion", "title", "url", false
    )

    private val apod2 = Apod(
        2, "copyright", "date",
        "explanation", "hdurl", "mediaType",
        "serviceVersion", "title", "url", false
    )

    @Test
    fun `getApods returns correct data`(): Unit = runBlocking {
        // Given

        val apodList = listOf(apod, apod2)
        val flowApodList: Flow<List<Apod>> = flowOf(apodList)

        val apodRoomDataSource = ApodRoomDataSource(
            apodDao = mock { apodDao ->
                onBlocking { apodDao.getAllApods() } doReturn flowApodList.map { apodList ->
                    apodList.map { apod -> apod.toEntity() }
                }
            })

        // When
        val getApods = apodRoomDataSource.getApods

        // Then
        Assert.assertEquals(apod, getApods.first()[0])
    }

    @Test
    fun `saveApod returns null when insert apod in db correctly`(): Unit = runBlocking {
        // Given
        wheneverBlocking { apodRoomDataSource.saveApod(apod) }.thenReturn(null)

        // When
        val error = apodRoomDataSource.saveApod(apod)

        // Then
        Assert.assertEquals(null, error)
    }

}