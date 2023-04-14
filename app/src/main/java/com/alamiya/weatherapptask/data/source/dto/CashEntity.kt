package com.alamiya.weatherapptask.data.source.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cash_table")
data class CashEntity(
    @PrimaryKey
    val cityName: String,
    val content:WeatherSuccessResponse,
    var createdAt:Long = System.currentTimeMillis()
)
