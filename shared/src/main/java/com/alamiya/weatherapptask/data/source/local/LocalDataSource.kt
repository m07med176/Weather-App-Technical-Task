package com.alamiya.weatherapptask.data.source.local

import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.local.room.CashDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val db: CashDao
) : ILocalDataSource {
    override fun getCash(city:String): Flow<CashEntity>  = db.getCash(city)

    override suspend fun insertCash(cash: CashEntity) {
        db.insertCash(cash)
    }

}