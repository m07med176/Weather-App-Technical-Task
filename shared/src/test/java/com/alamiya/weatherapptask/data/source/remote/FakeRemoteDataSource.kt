package com.alamiya.weatherapptask.data.source.remote

import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import retrofit2.Response

class FakeRemoteDataSource(
    var weatherSuccessResponse: WeatherSuccessResponse
) : IRemoteDataSource {

    override suspend fun getWeatherDetails(
        cityName: String
    ): Response<WeatherSuccessResponse>  = Response.success(weatherSuccessResponse)
}