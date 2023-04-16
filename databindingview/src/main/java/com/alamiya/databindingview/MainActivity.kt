package com.alamiya.databindingview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alamiya.databindingview.presentation.utils.WorkerManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WorkerManager.keepWorkerRunning(applicationContext)
    }
}