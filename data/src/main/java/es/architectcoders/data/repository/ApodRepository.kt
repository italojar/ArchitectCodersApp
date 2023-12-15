package es.architectcoders.data.repository

import es.architectcoders.data.source.network.service.ApodService
import javax.inject.Inject

class ApodRepository @Inject constructor(
    private val apodService: ApodService
) {

    suspend fun getApod() {
        apodService.getApod()
    }
}