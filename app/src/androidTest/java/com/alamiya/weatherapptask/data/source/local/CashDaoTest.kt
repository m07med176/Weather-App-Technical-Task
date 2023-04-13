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
            )
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
        val item  = cashDao.getCash().first()

        // Then: count of inserted items and retrieved are same
        assertThat(item.content.cod,`is`("200"))
    }

    @Test
    fun insertHome_insertItem_getInsertedItem() = runBlockingTest {
        // Given: fake data
        // When: insert fake data to room
        cashDao.insertCash(fakeData)

        // Then: get last inserted data from room
        val item  = cashDao.getCash().first()
        assertThat(item.content.cod,`is`("200"))
    }

    @Test
    fun deleteHome_deleteItem_getNullValue()  = runBlockingTest {
        // Given: insert fake data to room and retrieve it
        cashDao.insertCash(fakeData)
        val item  = cashDao.getCash().first()

        // When: delete inserted item
        cashDao.deleteCash(item)

        // Then: get data from room to check if null
        val result  = cashDao.getCash().first()
        assertThat(result, IsNull.nullValue())

    }
}