package es.architectcoders.spaceexplorer.framework.server.notificationsServer

import android.util.Log
import arrow.core.Either
import es.architectcoders.data.datasource.NotificationsRemoteDataSource
import es.architectcoders.domain.Error
import es.architectcoders.domain.NotificationsItem
import es.architectcoders.spaceexplorer.di.ApiKey
import es.architectcoders.spaceexplorer.framework.tryCall
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class NotificationsServerDataSource @Inject constructor(@ApiKey private val apiKey: String) :
    NotificationsRemoteDataSource {


    override suspend fun getNotifications(date: Calendar): Either<Error, List<NotificationsItem>> =
        tryCall {

//        // Obtener la fecha actual
//        val fechaActual = Calendar.getInstance()

// Clonar la fecha actual para tener una copia
            val fechaMenos7Dias = date.clone() as Calendar

// Restar 7 días a la fecha clonada
            fechaMenos7Dias.add(Calendar.DAY_OF_YEAR, -7)

// Obtener la fecha actual formateada
            val formatoFecha = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val fechaActualFormateada = formatoFecha.format(date.time)

// Obtener la fecha con 7 días menos formateada
            val fechaMenos7DiasFormateada = formatoFecha.format(fechaMenos7Dias.time)

            RemoteNotificationsConnection.service
                .getNotifications(
                    fechaMenos7DiasFormateada,
                    fechaActualFormateada,
                    "all",
                    apiKey
                )
                .toDomain()
        }

    private fun List<NotificationsItemResponse>.toDomain(): List<NotificationsItem> =
        map {
            Log.d("LIST RESPONSE SERVER DATA SOURCE///////", it.toString())
            it.toDomain()
        }

    private fun NotificationsItemResponse.toDomain(): NotificationsItem =
        NotificationsItem(
            messageType,
            messageID,
            messageURL,
            messageIssueTime,
            messageBody
        )
}