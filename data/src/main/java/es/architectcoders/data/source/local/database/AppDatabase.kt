package es.architectcoders.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.architectcoders.data.source.local.database.entity.ApodEntity

@Database(entities = [ApodEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): Dao
}