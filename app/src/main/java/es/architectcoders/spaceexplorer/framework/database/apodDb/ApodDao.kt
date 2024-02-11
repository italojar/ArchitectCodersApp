package es.architectcoders.spaceexplorer.framework.database.apodDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ApodDao {

    @Query("SELECT * FROM ApodEntity")
    fun getAllApods(): Flow<List<ApodEntity>>

    @Query("SELECT * FROM ApodEntity WHERE id = :id")
    suspend fun findApodById(id: Int): ApodEntity

    @Query("SELECT * FROM ApodEntity WHERE date = :date")
    suspend fun findApodByDate(date: String): ApodEntity?

    @Query("SELECT COUNT(id) FROM ApodEntity")
    suspend fun apodsCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApod(apodEntity: ApodEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateApod(apodEntity: ApodEntity)

}