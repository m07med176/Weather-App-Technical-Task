package com.alamiya.weatherapptask.domain.models

import com.alamiya.weatherapptask.data.source.dto.Weather
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.alamiya.weatherapptask.domain.utils.EntityMapper
import com.alamiya.weatherapptask.domain.utils.TimeConverter
import com.alamiya.weatherapptask.domain.utils.iconConverter

class WeatherResponseMapper:EntityMapper<WeatherSuccessResponse,WeatherResponseModel> {
    override fun mapFromEntity(entity: WeatherSuccessResponse): WeatherResponseModel
     = WeatherResponseModel(
        cityModel = entity.city,
        weatherList = entity.list.map {
            val weatherDesc = if (it.weather.isEmpty()) Weather() else it.weather[0]
            WeatherContentModel(
                dt = TimeConverter.convertTimestampToString(it.dt.toLong(), TimeConverter.DAY_PATTERN) ?: "",
                image = iconConverter(weatherDesc.icon),
                max = it.main.temp_max.toString(),
                min = it.main.temp_min.toString(),
                desc = weatherDesc.description,

                feels_like = it.main.feels_like.toString(),
                humidity = it.main.humidity.toString(),
                pressure = it.main.pressure.toString(),
                temp = it.main.temp.toString(),
                visibility = it.visibility.toString(),
                wind = it.wind.speed.toString(),
                cloud = it.clouds.all.toString(),
                windGust = it.wind.gust.toString()
            )
        }
    )

}