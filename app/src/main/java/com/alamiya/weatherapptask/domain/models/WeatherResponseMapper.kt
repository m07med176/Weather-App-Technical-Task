package com.alamiya.weatherapptask.domain.models

import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.alamiya.weatherapptask.domain.utils.EntityMapper

class WeatherResponseMapper:EntityMapper<WeatherSuccessResponse,WeatherResponseModel> {
    override fun mapFromEntity(entity: WeatherSuccessResponse): WeatherResponseModel
    = WeatherResponseModel(
        cityModel = entity.city,
        cnt = entity.cnt,
        cod = entity.cod,
        list = entity.list,
        message = entity.message
    )

    override fun entityFromMap(domainModel: WeatherResponseModel): WeatherSuccessResponse
    = WeatherSuccessResponse(
        city = domainModel.cityModel,
        cnt = domainModel.cnt,
        cod = domainModel.cod,
        list = domainModel.list,
        message = domainModel.message
    )
}