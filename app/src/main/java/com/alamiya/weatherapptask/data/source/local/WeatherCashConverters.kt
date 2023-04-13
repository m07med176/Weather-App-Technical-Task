package com.alamiya.weatherapptask.data.source.local

import androidx.room.TypeConverter
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.google.gson.Gson

class WeatherCashConverters {
    @TypeConverter
    fun fromStringToCashWeather(weather: String?): WeatherSuccessResponse? {
        return weather?.let { Gson().fromJson(it, WeatherSuccessResponse::class.java) }
    }

    @TypeConverter
    fun fromCashWeatherToString(weather: WeatherSuccessResponse?): String? {
        return weather?.let { Gson().toJson(it) }
    }
}