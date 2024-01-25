package es.architectcoders.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.architectcoders.framework.database.entity.ApodEntity

@Database(entities = [ApodEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getApodDao(): ApodDao
}