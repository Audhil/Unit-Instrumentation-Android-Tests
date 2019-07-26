package com.audhil.medium.gweatherapp.basic_mockito_samples.mocksample

import junit.framework.TestCase.assertNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doReturn
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

//  based on https://stackoverflow.com/questions/28295625/mockito-spy-vs-mock
@RunWith(MockitoJUnitRunner::class)
class MockVsSpy {

    @Mock
    lateinit var mockList: MutableList<String>

    @Spy
    var spyList = mutableListOf<String>()

    @Test
    fun testMockList() {
        mockList.add("0th item")    //  doesn't call `add()` func of mutableList
        Mockito.verify(mockList).add("0th item")
        assertEquals(0, mockList.size)
        assertNull(mockList[0])
    }

    @Test
    fun testSpyList() {
        spyList.add("0th item") //  calls `add()` func of mutableList
        Mockito.verify(spyList).add("0th item")
        assertEquals(1, spyList.size)
        assertNotNull(spyList[0])
    }

    //  with stubs
    @Test
    fun testMockListWithStub() {
        `when`(mockList[1999]).thenReturn("I'm the value @ 1999")   //  stubbing
        assertEquals("I'm the value @ 1999", mockList[1999])
    }

    @Test
    fun testSpyListWithStub() {
        //  stubbing a spy method will result the same as the mock object
//        doReturn("9").`when`(spyList).get(9)    //  take note of using doReturn instead of when - special for spy objects
        doReturn("9").`when`(spyList)[9]    //  take note of using doReturn instead of when - special for spy objects
        assertEquals("9", spyList[9])
    }
}