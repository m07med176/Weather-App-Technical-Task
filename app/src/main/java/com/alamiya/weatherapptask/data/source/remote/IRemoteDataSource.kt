package com.alamiya.weatherapptask.data.source.remote

import com.alamiya.weatherapptask.Constants
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import retrofit2.Response
import retrofit2.http.Query

interface IRemoteDataSource{
    suspend fun getWeatherDetails(
        cityName: String,
    ): Response<WeatherSuccessResponse>

}