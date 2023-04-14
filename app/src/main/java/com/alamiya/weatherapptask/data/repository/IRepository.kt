package com.alamiya.weatherapptask.data.repository

import android.content.Context
import com.alamiya.weatherapptask.data.source.local.ILocalDataSource
import com.alamiya.weatherapptask.data.source.remote.IRemoteDataSource

interface IRepository:ILocalDataSource,IRemoteDataSource{
    val _context: Context
    fun checkInternetConnectivity(): Boolean

}