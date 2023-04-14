package com.alamiya.weatherapptask.data.repository

import android.app.Application
import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.alamiya.weatherapptask.data.source.local.ILocalDataSource
import com.alamiya.weatherapptask.data.source.local.LocalDataSource
import com.alamiya.weatherapptask.data.source.local.room.RoomDB
import com.alamiya.weatherapptask.data.source.remote.IRemoteDataSource
import com.alamiya.weatherapptask.data.source.remote.RemoteDataSource
import com.alamiya.weatherapptask.data.source.remote.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class RepositoryImpl(
    private val local:ILocalDataSource,
    private val remote:IRemoteDataSource

) : IRepository {

    companion object{
        @Volatile
        private var INSTANCE: RepositoryImpl? = null

        fun getInstance(app:Application):RepositoryImpl{
            return INSTANCE?: synchronized(this){
                // Local Dependencies
                val room   = RoomDB.invoke(app)
                val localDataSource = LocalDataSource(room.cashDao())

                // Remote Dependencies
                val api = RetrofitInstance(app).api
                val remoteDataSource = RemoteDataSource(api)

                RepositoryImpl(localDataSource,remoteDataSource)

            }
        }
    }
    override fun getCash(city:String): Flow<CashEntity>  = local.getCash(city)

    override suspend fun insertCash(cash: CashEntity) {
        local.insertCash(cash)
    }

    override suspend fun deleteCash(createdAt: Long) {
        local.deleteCash(createdAt)
    }

    override suspend fun getWeatherDetails(cityName: String): Response<WeatherSuccessResponse> =
        remote.getWeatherDetails(cityName)
}