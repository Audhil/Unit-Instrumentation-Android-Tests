package com.audhil.medium.gweatherapp.basic_mockito_samples

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class SomeClassTest {

    private var someClass: SomeClass? = null

    @Before
    fun setUp() {
        someClass = SomeClass()
    }

    @Test(expected = Exception::class)
    fun testExceptionIsThrown() {
        someClass?.multiply(101, 3)
    }

    @Test
    fun testMultiply() {
        val result = someClass?.multiply(3, 4)
        assertEquals("3 x 4 = 12", 12, result)
    }
}