package com.alamiya.weatherapptask.data.source.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cash_table")
data class CashEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val content:WeatherSuccessResponse
)
