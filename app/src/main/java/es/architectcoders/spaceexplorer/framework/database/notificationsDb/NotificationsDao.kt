package es.architectcoders.spaceexplorer.framework.database.notificationsDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationsDao {

    @Query("SELECT * FROM NotificationsItemEntity")
    fun getAllNotifications(): Flow<List<NotificationsItemEntity>>

    @Query("SELECT COUNT(id) FROM NotificationsItemEntity")
    suspend fun notificationsCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotifications(notifications: List<NotificationsItemEntity>)
}