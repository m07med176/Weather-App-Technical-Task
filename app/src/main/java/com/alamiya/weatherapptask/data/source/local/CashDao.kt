package com.alamiya.weatherapptask.data.source.local

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
    suspend fun insertCash(home: CashEntity)

    @Delete
    suspend fun deleteCash(home: CashEntity)
}