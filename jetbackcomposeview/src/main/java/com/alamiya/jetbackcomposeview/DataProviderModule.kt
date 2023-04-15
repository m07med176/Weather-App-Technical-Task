package com.alamiya.jetbackcomposeview

import android.app.Application
import com.alamiya.weatherapptask.data.repository.IRepository
import com.alamiya.weatherapptask.data.repository.RepositoryImpl
import com.alamiya.weatherapptask.data.source.local.ILocalDataSource
import com.alamiya.weatherapptask.data.source.local.LocalDataSource
import com.alamiya.weatherapptask.data.source.local.room.RoomDB
import com.alamiya.weatherapptask.data.source.remote.IRemoteDataSource
import com.alamiya.weatherapptask.data.source.remote.RemoteDataSource
import com.alamiya.weatherapptask.data.source.remote.retrofit.RetrofitInstance
import com.alamiya.weatherapptask.domain.usecase.GetRegionsName
import com.alamiya.weatherapptask.domain.usecase.GetWeatherDetailsUseCase
import com.alamiya.weatherapptask.domain.usecase.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataProviderModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(app: Application): ILocalDataSource {
        val room = RoomDB.invoke(app)
        return LocalDataSource(room.cashDao())
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(): IRemoteDataSource {
        val api = RetrofitInstance().api
        return RemoteDataSource(api)
    }

    @Provides
    @Singleton
    fun provideRepository(
        localDataSource: ILocalDataSource,
        remoteDataSource: IRemoteDataSource,
        app: Application
    ): IRepository {
        return RepositoryImpl(localDataSource, remoteDataSource, app.applicationContext)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: IRepository): UseCases {
        return UseCases(
            GetWeatherDetailsUseCase(repository),
            GetRegionsName(repository)
        )
    }
}

