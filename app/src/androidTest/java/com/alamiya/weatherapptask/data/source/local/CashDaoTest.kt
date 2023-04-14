package com.alamiya.weatherapptask.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.dto.City
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.alamiya.weatherapptask.data.source.local.room.CashDao
import com.alamiya.weatherapptask.data.source.local.room.RoomDB
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CashDaoTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var cashDao: CashDao
    private lateinit var database: RoomDB
    private lateinit var fakeData:CashEntity

    @Before
    fun initDB(){
        fakeData = CashEntity(

            content = WeatherSuccessResponse(
                city = City(),
                cnt = 563,
                cod = "200" ,
                list = listOf(),
                message = 65
            ),
            cityName = "London"
        )

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RoomDB::class.java
        ).allowMainThreadQueries().build()
        cashDao = database.cashDao()
    }

    @After
    fun closeDB() = database.close()

    @Test
    fun getCash_insertItems_getCheckedItem()  = runBlockingTest {
        // Given: insert single item in room
        cashDao.insertCash(fakeData)

        // When: retrieve single items from room
        val item  = cashDao.getCash(fakeData.cityName).first()

        // Then: count of inserted items and retrieved are same
        assertThat(item.content.cod,`is`("200"))
    }

    @Test
    fun insertHome_insertItem_getInsertedItem() = runBlockingTest {
        // Given: fake data
        // When: insert fake data to room
        cashDao.insertCash(fakeData)

        // Then: get last inserted data from room
        val item  = cashDao.getCash(fakeData.cityName).first()
        assertThat(item.content.cod,`is`("200"))
    }

    @Test
    fun deleteHome_deleteItem_getNullValue()  = runBlockingTest {
        // Given:
        //      - get current time in milli and get different time
        //      - insert fake data with different time to room and retrieve it

        val currentTimeMilli = System.currentTimeMillis()
        val MAX_AGE = 7
        val maxAgeInMilli = MAX_AGE * 24 * 60 * 60 * 1000
        val differentTime = currentTimeMilli - maxAgeInMilli

        val dateFormate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        println("Date is ==> ${dateFormate.format(Date(differentTime))}")

        fakeData.createdAt = differentTime
        cashDao.insertCash(fakeData)
        val item  = cashDao.getCash(fakeData.cityName).first()

        // When: delete inserted item
        cashDao.deleteCash(currentTimeMilli)

        // Then: get data from room to check if null
        val result  = cashDao.getCash(fakeData.cityName).first()
        assertThat(result, IsNull.nullValue())

    }
}