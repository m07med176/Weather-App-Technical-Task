package com.alamiya.weatherapptask.data.repository

import com.alamiya.weatherapptask.data.source.local.ILocalDataSource
import com.alamiya.weatherapptask.data.source.remote.IRemoteDataSource

interface IRepository:ILocalDataSource,IRemoteDataSource