package com.alamiya.weatherapptask.domain.usecase

import com.alamiya.weatherapptask.data.repository.IRepository
import com.alamiya.weatherapptask.domain.models.WeatherCashMapper
import com.alamiya.weatherapptask.domain.models.WeatherResponseModel

class DeleteCashUseCase(private val _repo:IRepository) {

    suspend operator fun invoke(createAt:Long){
        _repo.deleteCash(createAt)
    }
}