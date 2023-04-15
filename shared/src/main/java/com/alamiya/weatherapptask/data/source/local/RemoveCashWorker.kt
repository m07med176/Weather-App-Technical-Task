package com.alamiya.weatherapptask.data.source.local

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.alamiya.weatherapptask.Constants
import com.alamiya.weatherapptask.data.source.local.room.RoomDB

class RemoveCashWorker(private val _context:Context,workerParams: WorkerParameters):
    CoroutineWorker(_context,workerParams){
    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {

        if (!isStopped){
            val differentTime = System.currentTimeMillis() - Constants.MAX_AGE_MILLI
            RoomDB.invoke(_context).cashDao().deleteCash(differentTime)
        }

        return Result.Success()
    }
}