package es.architectcoders.domain.usecase

import android.util.Log
import es.architectcoders.data.repository.ApodRepository
import javax.inject.Inject

class GetApodUseCase @Inject constructor(
    private val apodRepository: ApodRepository
) {

    suspend operator fun invoke(): String {
        val apod = apodRepository.getApod()
        return if (apod != null) {
            Log.d("GetApodUseCase", apod.toString())
            "$apod"
        } else {
            "error"
        }
    }

}