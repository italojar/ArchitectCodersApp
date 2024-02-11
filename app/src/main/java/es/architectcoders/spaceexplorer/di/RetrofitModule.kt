package es.architectcoders.spaceexplorer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.architectcoders.spaceexplorer.BuildConfig
import es.architectcoders.spaceexplorer.framework.server.apodServer.ApodApiClient
import es.architectcoders.spaceexplorer.framework.server.NasaInterceptor
import es.architectcoders.spaceexplorer.framework.server.roverServer.RoversApiClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(nasaClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(nasaClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApodApiClient(retrofit: Retrofit): ApodApiClient {
        return retrofit.create(ApodApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideRoversApiClient(retrofit: Retrofit): RoversApiClient {
        return retrofit.create(RoversApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideClient(interceptor: NasaInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}