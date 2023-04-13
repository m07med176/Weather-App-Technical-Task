package com.alamiya.weatherapptask.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.alamiya.weatherapptask.data.source.dto.CashEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CashDao {

    @Query("SELECT * FROM cash_table ORDER BY id DESC LIMIT 1")
    fun getCash(): Flow<CashEntity>

    @Upsert
    suspend fun insertCash(cash: CashEntity)

    @Delete
    suspend fun deleteCash(cash: CashEntity)
}