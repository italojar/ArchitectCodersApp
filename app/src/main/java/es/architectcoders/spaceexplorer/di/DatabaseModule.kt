package es.architectcoders.spaceexplorer.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.architectcoders.spaceexplorer.framework.database.AppDatabase
import es.architectcoders.spaceexplorer.framework.database.apodDb.ApodDao
import es.architectcoders.spaceexplorer.framework.database.roverDb.RoversDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "DatabaseApp"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
          return Room.databaseBuilder(
              appContext, AppDatabase::class.java, DATABASE_NAME
          ).build()
    }

    @Provides
    @Singleton
    fun providedDao(appDatabase: AppDatabase): ApodDao = appDatabase.getApodDao()

    @Provides
    @Singleton
    fun providedRoversDao(appDatabase: AppDatabase): RoversDao = appDatabase.getRoversDao()
}