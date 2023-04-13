package com.alamiya.weatherapptask.data.source.local

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.alamiya.weatherapptask.data.source.dto.CashEntity
import kotlinx.coroutines.flow.Flow

interface CashDao {

    @Query("SELECT * FROM cash_table ORDER BY id DESC LIMIT 1")
    fun getCash(): Flow<CashEntity>

    @Upsert
    suspend fun insertHome(home: CashEntity)

    @Delete
    suspend fun deleteHome(home: CashEntity)
}