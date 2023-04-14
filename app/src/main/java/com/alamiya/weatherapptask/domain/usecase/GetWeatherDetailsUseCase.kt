package com.alamiya.weatherapptask.domain.usecase

import com.alamiya.weatherapptask.data.repository.IRepository
import com.alamiya.weatherapptask.domain.models.WeatherResponseModel
import com.alamiya.weatherapptask.domain.utils.DataResponseState
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

class GetWeatherDetailsUseCase(private val _repo:IRepository) {
//    suspend operator fun invoke(city:String):Flow<DataResponseState<WeatherResponseModel>>
//
//    = _repo.getWeatherDetails(city)
}