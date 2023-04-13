package com.alamiya.weatherapptask.data.source.local

import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.local.room.CashDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val db: CashDao
) : ILocalDataSource {
    override fun getCash(): Flow<CashEntity>  = db.getCash()

    override suspend fun insertCash(cash: CashEntity) {
        db.insertCash(cash)
    }

    override suspend fun deleteCash(cash: CashEntity) {
        db.deleteCash(cash)
    }
}