package es.architectcoders.data.source.local


import es.architectcoders.data.database.ApodDao
import es.architectcoders.data.mappers.toData
import es.architectcoders.data.mappers.toEntity
import es.architectcoders.data.source.local.model.ApodData
import es.architectcoders.data.source.network.model.ApodResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ApodLocalDataSource @Inject constructor(
    private val apodDao: ApodDao
) {
    suspend fun saveApod(apodResponse: ApodResponse) {
        apodDao.insertApod(apodResponse.toEntity())
    }

    fun getApods(): Flow<List<ApodData>> {
        return apodDao.getAllApods().map { apodList ->
            apodList.map { apodEntity -> apodEntity.toData() }
        }
    }

    suspend fun isApodEmpty(): Boolean  = apodDao.apodsCount() == 0

    suspend fun apodExists(apodResponse: ApodResponse): Boolean {
        return apodDao.findApodByDate(apodResponse.date) != null
    }

    suspend fun saveApodAsFavourite(apodData: ApodData) {
        apodDao.updateApod(apodData.toEntity())
    }
}