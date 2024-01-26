package es.architectcoders.spaceexplorer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.architectcoders.data.datasource.ApodLocalDataSource
import es.architectcoders.data.datasource.ApodRemoteDataSource
import es.architectcoders.spaceexplorer.framework.database.ApodRoomDataSource
import es.architectcoders.spaceexplorer.framework.server.ApodServerDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSource: ApodRoomDataSource): ApodLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: ApodServerDataSource): ApodRemoteDataSource
}