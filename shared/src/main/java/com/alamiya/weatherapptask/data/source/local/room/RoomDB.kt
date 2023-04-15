package com.alamiya.weatherapptask.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alamiya.weatherapptask.data.source.dto.CashEntity

@Database(
    entities = [CashEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(WeatherCashConverters::class)
abstract class RoomDB : RoomDatabase() {
    abstract fun cashDao(): CashDao

    companion object {
        @Volatile
        private var instance: RoomDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RoomDB::class.java,
                "room.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}