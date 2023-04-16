package com.alamiya.databindingview.presentation.weatherDetails

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.alamiya.databindingview.MainActivity
import com.alamiya.databindingview.R
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class WeatherDetailsFragmentTest {

    @Test
    fun searchTask() = runBlocking {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.auto_complete_text_view))
            .perform(click())
            .perform(ViewActions.typeText("cairo"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.auto_complete_text_view)).perform(
            click()
        )

        onView(withId(R.id.auto_complete_text_view))
            .perform(click())
            .perform(ViewActions.clearText())
            .perform(ViewActions.typeText("london"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.auto_complete_text_view)).perform(
            click()
        )

        onView(withId(R.id.auto_complete_text_view))
            .perform(click())
            .perform(ViewActions.clearText())
            .perform(ViewActions.typeText("alex"), ViewActions.closeSoftKeyboard())

        activityScenario.close()
    }

}

