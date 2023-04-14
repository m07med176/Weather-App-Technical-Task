package com.alamiya.weatherapptask.data.source.local

import androidx.annotation.RawRes
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.dto.countries.CountriesItem
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource{

    fun getCash(city:String): Flow<CashEntity>

    suspend fun insertCash(cash: CashEntity)



}