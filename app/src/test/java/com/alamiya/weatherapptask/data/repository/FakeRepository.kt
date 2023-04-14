package com.alamiya.weatherapptask.data.repository

import android.content.Context
import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.alamiya.weatherapptask.data.source.local.ILocalDataSource
import com.alamiya.weatherapptask.data.source.remote.IRemoteDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class FakeRepository(override val context: Context,
                     override val local: ILocalDataSource,
                     override val remote: IRemoteDataSource,
                     var isOnline: Boolean = true
) :IRepository {
    override fun checkInternetConnectivity(): Boolean  = isOnline

    override fun getCash(city: String): Flow<CashEntity>  = local.getCash(city)

    override suspend fun insertCash(cash: CashEntity) {
        local.insertCash(cash)
    }

    override suspend fun getWeatherDetails(cityName: String): Response<WeatherSuccessResponse>
    = remote.getWeatherDetails(cityName)
}