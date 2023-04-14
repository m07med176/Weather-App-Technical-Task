package com.alamiya.weatherapptask.domain.usecase

import androidx.annotation.RawRes
import com.alamiya.weatherapptask.data.repository.IRepository
import com.alamiya.weatherapptask.domain.models.CountriesMapper
import com.alamiya.weatherapptask.domain.models.RegionsName
import com.alamiya.weatherapptask.domain.models.WeatherResponseModel
import com.alamiya.weatherapptask.domain.utils.DataResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRegionsName(private val _repo: IRepository) {
    operator fun invoke(@RawRes id:Int): Flow<List<RegionsName>> =
        _repo.getCountries(id).map {
            CountriesMapper().mapFromEntity(it)
        }
}