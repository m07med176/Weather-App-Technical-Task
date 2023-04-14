package com.alamiya.weatherapptask.domain.models

import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.alamiya.weatherapptask.domain.utils.EntityMapper

class WeatherCashMapper: EntityMapper<CashEntity,WeatherResponseModel> {
    override fun mapFromEntity(entity: CashEntity): WeatherResponseModel
    = WeatherResponseModel(
        cityModel = entity.content.city,
        cnt = entity.content.cnt,
        cod = entity.content.cod,
        list = entity.content.list,
        message = entity.content.message
    )

    override fun entityFromMap(domainModel: WeatherResponseModel): CashEntity
    = CashEntity(
        content =  WeatherSuccessResponse(
        city = domainModel.cityModel,
        cnt = domainModel.cnt,
        cod = domainModel.cod,
        list = domainModel.list,
        message = domainModel.message
    ),
    cityName = domainModel.cityModel.name?:"UnKnown" )



}