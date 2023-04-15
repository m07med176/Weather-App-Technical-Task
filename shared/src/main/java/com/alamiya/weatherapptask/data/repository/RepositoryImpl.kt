package com.alamiya.weatherapptask.data.repository

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.annotation.RawRes
import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.alamiya.weatherapptask.data.source.dto.countries.CountriesItem
import com.alamiya.weatherapptask.data.source.local.ILocalDataSource
import com.alamiya.weatherapptask.data.source.local.LocalDataSource
import com.alamiya.weatherapptask.data.source.local.room.RoomDB
import com.alamiya.weatherapptask.data.source.remote.IRemoteDataSource
import com.alamiya.weatherapptask.data.source.remote.RemoteDataSource
import com.alamiya.weatherapptask.data.source.remote.hasNetwork
import com.alamiya.weatherapptask.data.source.remote.retrofit.RetrofitInstance
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStreamReader

class RepositoryImpl(
    override val local:ILocalDataSource,
    override val remote:IRemoteDataSource,
    override val context: Context
    ) : IRepository {


    companion object{

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: RepositoryImpl? = null

        fun getInstance(app:Application):RepositoryImpl{
            return INSTANCE?: synchronized(this){
                // Local Dependencies
                val room   = RoomDB.invoke(app)
                val localDataSource = LocalDataSource(room.cashDao())

                // Remote Dependencies
                val api = RetrofitInstance().api
                val remoteDataSource = RemoteDataSource(api)

                RepositoryImpl(localDataSource,remoteDataSource,app)

            }
        }
    }
    override fun getCash(city:String): Flow<CashEntity>  = local.getCash(city)

    override suspend fun insertCash(cash: CashEntity) {
        local.insertCash(cash)
    }


    override suspend fun getWeatherDetails(cityName: String): Response<WeatherSuccessResponse> =
        remote.getWeatherDetails(cityName)


    override fun checkInternetConnectivity(): Boolean = context.hasNetwork()

    override fun getCountries(@RawRes id:Int):Flow<List<CountriesItem>> = flow{
        val inputStream = context.resources.openRawResource(id)
        val jsonString = BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
        val data = Gson().fromJson(jsonString, Array<CountriesItem>::class.java).toList()
        emit(data)
    }
}