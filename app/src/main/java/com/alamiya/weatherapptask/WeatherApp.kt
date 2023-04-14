package com.alamiya.weatherapptask

import android.app.Application
import androidx.work.*
import com.alamiya.weatherapptask.data.source.local.RemoveCashWorker
import java.util.*
import java.util.concurrent.TimeUnit

class WeatherApp:Application() {
    override fun onCreate() {
        super.onCreate()
        keepWorkerRunning()
    }
    private fun keepWorkerRunning(){
        if (checkIfWorkerRunning())
            initializeWorker()
    }

    private fun checkIfWorkerRunning():Boolean{
        val worker = WorkManager.getInstance(applicationContext).getWorkInfoById(UUID(Constants.WORKER_ID,0))
        return worker.isCancelled
    }
    private fun initializeWorker(){
        val data  = Data.Builder()
        data.putLong("id",Constants.WORKER_ID)

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        val periodicWokrRequest = PeriodicWorkRequest.Builder(
            RemoveCashWorker::class.java,24, TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .setInputData(data.build())
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "${Constants.WORKER_ID}",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            periodicWokrRequest
        )

    }
}