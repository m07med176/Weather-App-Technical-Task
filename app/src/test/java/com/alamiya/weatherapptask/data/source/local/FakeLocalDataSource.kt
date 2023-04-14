package com.alamiya.weatherapptask.data.source.local

import com.alamiya.weatherapptask.data.source.dto.CashEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocalDataSource(
    var cash: CashEntity? =null
) : ILocalDataSource {
    override fun getCash(city:String): Flow<CashEntity> = flow {
        cash?.let { emit(it) }

    }

    override suspend fun insertCash(cash: CashEntity) {
        this.cash = cash
    }

}