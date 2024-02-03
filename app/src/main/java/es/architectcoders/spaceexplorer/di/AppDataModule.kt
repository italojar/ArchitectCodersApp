package es.architectcoders.spaceexplorer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.architectcoders.data.datasource.ApodLocalDataSource
import es.architectcoders.data.datasource.ApodRemoteDataSource
import es.architectcoders.data.datasource.RoversLocalDataSource
import es.architectcoders.data.datasource.RoversRemoteDataSource
import es.architectcoders.spaceexplorer.framework.database.ApodRoomDataSource
import es.architectcoders.spaceexplorer.framework.database.RoversRoomDataSource
import es.architectcoders.spaceexplorer.framework.server.ApodServerDataSource
import es.architectcoders.spaceexplorer.framework.server.RoversServerDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSource: ApodRoomDataSource): ApodLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: ApodServerDataSource): ApodRemoteDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RoversServerDataSource): RoversRemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSource: RoversRoomDataSource): RoversLocalDataSource
}