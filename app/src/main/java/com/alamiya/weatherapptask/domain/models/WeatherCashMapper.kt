package com.alamiya.weatherapptask.domain.models

import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.dto.Weather
import com.alamiya.weatherapptask.data.source.dto.WeatherContent
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.alamiya.weatherapptask.domain.utils.EntityMapper
import com.alamiya.weatherapptask.domain.utils.TimeConverter
import com.alamiya.weatherapptask.domain.utils.iconConverter

class WeatherCashMapper: EntityMapper<CashEntity,WeatherResponseModel> {
    override fun mapFromEntity(entity: CashEntity): WeatherResponseModel
    = WeatherResponseModel(
        cityModel = entity.content.city,
        weatherList = entity.content.list.map {
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
            )
        }
    )



}