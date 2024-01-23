package es.architectcoders.data.source.local


import es.architectcoders.data.database.ApodDao
import es.architectcoders.data.mappers.toDomain
import es.architectcoders.data.mappers.toEntity
import es.architectcoders.data.source.network.model.ApodResponse
import es.architectcoders.domain.model.Apod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ApodLocalDataSource @Inject constructor(
    private val apodDao: ApodDao
) {
    suspend fun saveApod(apod: ApodResponse) {
        apodDao.insertApod(apod.toEntity())
    }

    fun getApods(): Flow<List<Apod>> {
        return apodDao.getAllApods().map { apodList ->
            apodList.map { apodEntity -> apodEntity.toDomain() }
        }
    }

    suspend fun isApodEmpty(): Boolean  = apodDao.apodsCount() == 0

    suspend fun apodExists(apod: ApodResponse): Boolean {
        return apodDao.findApodByDate(apod.toEntity().date) != null
    }

    suspend fun saveApodAsFavourite(apod: Apod) {
        apodDao.updateApod(apod.toEntity())
    }
}