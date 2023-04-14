package com.alamiya.weatherapptask.data.source.remote

import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.alamiya.weatherapptask.data.source.remote.retrofit.CallApi
import retrofit2.Response

class FakeRemoteDataSource(
    var weatherSuccessResponse: WeatherSuccessResponse
) : IRemoteDataSource {

    override suspend fun getWeatherDetails(
        cityName: String
    ): Response<WeatherSuccessResponse>  = Response.success(weatherSuccessResponse)

    override fun checkInternetConnectivity(): Boolean  = true
}