package com.alamiya.weatherapptask.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alamiya.weatherapptask.data.source.remote.retrofit.CallApi
import com.alamiya.weatherapptask.data.source.remote.retrofit.RetrofitInstance
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [32])
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CallApiTest{
    private lateinit var retrofitInstance: RetrofitInstance
    private lateinit var api: CallApi

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun initRetrofit() {
        retrofitInstance = RetrofitInstance(ApplicationProvider.getApplicationContext())
        api = retrofitInstance.api
    }

    @Test
    fun getWeatherDetails_requestAPI_isSuccessful() = runBlocking {
        // When
        val response = async {
            api.getWeatherDetails(cityName = "London")
        }
        // Then
        MatcherAssert.assertThat(response.await().body()?.cod, Is.`is`("200"))
        MatcherAssert.assertThat(response.await().body(), CoreMatchers.notNullValue())
        MatcherAssert.assertThat(response.await().errorBody(), CoreMatchers.nullValue())
    }
}