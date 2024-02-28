package es.architectcoders.spaceexplorer.framework.database.roverDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import es.architectcoders.spaceexplorer.framework.database.roverDb.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoversDao {

    @Query("SELECT * FROM PhotoEntity")
    fun getAllRovers(): Flow<List<PhotoEntity>>

    @Query("SELECT COUNT(id) FROM PhotoEntity")
    suspend fun roversCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRovers(rovers: List<PhotoEntity>)

}