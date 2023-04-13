package com.alamiya.weatherapptask.data.source.remote

import com.alamiya.weatherapptask.Constants
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CallApi {

    @GET("forecast")
    suspend fun getWeatherDetails(
        @Query("appid") apiKey: String = Constants.API_KEY,
        @Query("q") cityName: String,
        ):Response<WeatherSuccessResponse>
}