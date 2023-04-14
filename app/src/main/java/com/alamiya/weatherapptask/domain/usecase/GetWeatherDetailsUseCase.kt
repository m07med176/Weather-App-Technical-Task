package com.alamiya.weatherapptask.domain.usecase

import com.alamiya.weatherapptask.data.repository.IRepository
import com.alamiya.weatherapptask.domain.models.WeatherCashMapper
import com.alamiya.weatherapptask.domain.models.WeatherResponseMapper
import com.alamiya.weatherapptask.domain.models.WeatherResponseModel
import com.alamiya.weatherapptask.domain.utils.DataResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetWeatherDetailsUseCase(private val _repo:IRepository) {
    operator fun invoke(city:String): Flow<DataResponseState<WeatherResponseModel>> =
        if( _repo.checkInternetConnectivity()) onlineMode(city)
        else  offlineMode(city)




    private fun onlineMode(city:String): Flow<DataResponseState<WeatherResponseModel>> = flow {
        val response = _repo.getWeatherDetails(city)

        if (response.isSuccessful){
            val responseData = response.body()
            responseData?.let {
                val data = WeatherResponseMapper().mapFromEntity(it)

                // Insert Data in Cash Room
                _repo.insertCash(WeatherCashMapper().entityFromMap(data))

                // Send Data to state
                emit(DataResponseState.OnSuccess(data))
            }
        }else{
            emit(DataResponseState.OnError("Error Happend"))
        }
    }

    private fun offlineMode(city:String): Flow<DataResponseState<WeatherResponseModel>> = flow {
        _repo.getCash(city).catch {
            emit(DataResponseState.OnError(it.message.toString()))
        }
            .collect{cashEntity->
                if (cashEntity!= null)
                    emit(DataResponseState.OnSuccess(WeatherCashMapper().mapFromEntity(cashEntity)))
                else
                    emit(DataResponseState.OnError("No Cashed Data"))
            }
    }


}


