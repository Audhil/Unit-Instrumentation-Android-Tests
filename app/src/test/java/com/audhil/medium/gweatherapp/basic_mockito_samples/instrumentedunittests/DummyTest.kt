package com.audhil.medium.gweatherapp.basic_mockito_samples.instrumentedunittests

import android.content.Context
import android.content.Intent
import com.audhil.medium.gweatherapp.ui.listing.ListingActivity
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

//  hints: http://tools.android.com/tech-docs/unit-testing-support#TOC-Method-...-not-mocked.-

@RunWith(MockitoJUnitRunner::class)
class DummyTest {

    @Mock
    lateinit var context: Context

//    @Mock
//    lateinit var intent: Intent

//    @Rule
//    @JvmField
//    val rule = InstantTaskExecutorRule()

    @InjectMocks
    lateinit var util: Util

    @Test
    fun `check correct extras`() {
        val intent = util.createQuery(context, "jack", "jill")
        assertNotNull(intent)


        val bundle = intent.extras
        assertNull(bundle)
//
//        assertEquals("first check", "jack", bundle?.getString("query"))
//        assertEquals("second check", "jill",intent.extras?.getString("value"))
    }
}

open class Util {
    fun createQuery(context: Context, query: String, value: String): Intent {
        val intent = Intent(context, ListingActivity::class.java)
        intent.putExtra("query", query)
        intent.putExtra("value", value)
        return intent
    }
}