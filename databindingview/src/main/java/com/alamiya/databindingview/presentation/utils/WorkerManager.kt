package com.alamiya.databindingview.presentation.utils

import android.content.Context
import androidx.work.*
import com.alamiya.weatherapptask.Constants
import com.alamiya.weatherapptask.data.source.local.RemoveCashWorker
import java.util.*
import java.util.concurrent.TimeUnit

class WorkerManager {
    companion object {

        fun keepWorkerRunning(context: Context) {
            if (checkIfWorkerRunning(context))
                initializeWorker(context)
        }

        private fun checkIfWorkerRunning(context: Context): Boolean {
            val worker = WorkManager.getInstance(context)
                .getWorkInfoById(UUID(Constants.WORKER_ID, 0))
            return worker.isCancelled
        }

        private fun initializeWorker(context: Context) {
            val data = Data.Builder()
            data.putLong("id", Constants.WORKER_ID)

            val constraints = Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .build()

            val periodicWokrRequest = PeriodicWorkRequest.Builder(
                RemoveCashWorker::class.java, 24, TimeUnit.HOURS
            )
                .setConstraints(constraints)
                .setInputData(data.build())
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "${Constants.WORKER_ID}",
                ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
                periodicWokrRequest
            )

        }
    }
}