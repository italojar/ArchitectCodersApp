package es.architectcoders.spaceexplorer.framework.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import es.architectcoders.spaceexplorer.framework.database.apodDb.ApodDao
import es.architectcoders.spaceexplorer.framework.database.apodDb.ApodEntity
import es.architectcoders.spaceexplorer.framework.database.roverDb.PhotoEntity
import es.architectcoders.spaceexplorer.framework.database.roverDb.RoversDao

@Database(entities = [ApodEntity::class, PhotoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getApodDao(): ApodDao
    abstract fun getRoversDao(): RoversDao
}