package com.alamiya.weatherapptask.data.repository

import android.content.Context
import androidx.annotation.RawRes
import com.alamiya.weatherapptask.data.source.dto.countries.CountriesItem
import com.alamiya.weatherapptask.data.source.local.ILocalDataSource
import com.alamiya.weatherapptask.data.source.remote.IRemoteDataSource
import com.alamiya.weatherapptask.domain.models.RegionsName
import kotlinx.coroutines.flow.Flow

interface IRepository:ILocalDataSource,IRemoteDataSource{
    val context: Context
    val local:ILocalDataSource
    val remote:IRemoteDataSource
    fun checkInternetConnectivity(): Boolean

    fun getCountries(@RawRes id:Int): Flow<List<CountriesItem>>
}