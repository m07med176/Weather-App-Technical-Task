package com.alamiya.weatherapptask.data.source.local

import com.alamiya.weatherapptask.data.source.dto.CashEntity
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource{

    fun getCash(city:String): Flow<CashEntity>

    suspend fun insertCash(cash: CashEntity)



}