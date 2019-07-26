package com.audhil.medium.gweatherapp.tests

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.audhil.medium.gweatherapp.R
import com.audhil.medium.gweatherapp.dispatcher.MockServerDispatcher
import com.audhil.medium.gweatherapp.ui.main.MainActivity
import com.audhil.medium.gweatherapp.util.ConstantsUtil
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var mockServer: MockWebServer

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start(8080)
    }

    @After
    fun tearDown() = mockServer.shutdown()

    @Test
    fun happyCase() {
        mockServer.dispatcher = MockServerDispatcher.ResponseDispatcher()
        val intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, MainActivity::class.java)
        intent.putExtra(
            ConstantsUtil.MOCK_URL,
            mockServer.url("/v1/forecast.json?key=41c23902be8e47c0a1d171804190206&q=13.0827,80.2707&days=5").toString()
        )
        rule.launchActivity(intent)

        //  degree text view - visible on success response
        Espresso.onView(ViewMatchers.withId(R.id.current_temp_txt_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //  city text view - visible on success response
        Espresso.onView(ViewMatchers.withId(R.id.city_txt_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //  loading view - not visible on success response
        Espresso.onView(ViewMatchers.withId(R.id.loading_layout))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))

        //  error view - not visible on success response
        Espresso.onView(ViewMatchers.withId(R.id.failure_layout))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun unHappyCase() {
        mockServer.dispatcher = MockServerDispatcher.ErrorDispatcher()
        val intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, MainActivity::class.java)
        intent.putExtra(
            ConstantsUtil.MOCK_URL,
            mockServer.url("I'm a mock URL").toString()
        )
        rule.launchActivity(intent)
        //  failure layout visible
        Espresso.onView(ViewMatchers.withId(R.id.failure_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}