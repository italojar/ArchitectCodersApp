package es.architectcoders.spaceexplorer.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ApodEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getApodDao(): ApodDao
}