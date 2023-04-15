package com.alamiya.weatherapptask.data.repository

import android.content.Context
import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.alamiya.weatherapptask.data.source.dto.countries.CountriesItem
import com.alamiya.weatherapptask.data.source.local.ILocalDataSource
import com.alamiya.weatherapptask.data.source.remote.IRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class FakeRepository(override val context: Context,
                     override val local: ILocalDataSource,
                     override val remote: IRemoteDataSource,
                     var isOnline: Boolean = true
) :IRepository {
    override fun checkInternetConnectivity(): Boolean  = isOnline
    override fun getCountries(id: Int): Flow<List<CountriesItem>>
       = flow {
        emit(listOf(
            CountriesItem("Capital1", "Countries1", listOf()),
            CountriesItem("Capital2", "Countries2", listOf()),
            CountriesItem("Capital3", "Countries3", listOf()),
        ))
    }


    override fun getCash(city: String): Flow<CashEntity>  = local.getCash(city)

    override suspend fun insertCash(cash: CashEntity) {
        local.insertCash(cash)
    }

    override suspend fun getWeatherDetails(cityName: String): Response<WeatherSuccessResponse>
    = remote.getWeatherDetails(cityName)
}