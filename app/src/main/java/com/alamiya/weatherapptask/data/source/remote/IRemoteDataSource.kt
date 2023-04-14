package com.alamiya.weatherapptask.data.source.remote

import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import retrofit2.Response

interface IRemoteDataSource{
    suspend fun getWeatherDetails(
        cityName: String,
    ): Response<WeatherSuccessResponse>


}