package com.alamiya.weatherapptask.data.source.remote

import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.alamiya.weatherapptask.data.source.remote.retrofit.CallApi
import retrofit2.Response

class RemoteDataSource(
    private val api:CallApi
) : IRemoteDataSource {
    override suspend fun getWeatherDetails(
        cityName: String
    ): Response<WeatherSuccessResponse>  = api.getWeatherDetails(cityName = cityName)

}