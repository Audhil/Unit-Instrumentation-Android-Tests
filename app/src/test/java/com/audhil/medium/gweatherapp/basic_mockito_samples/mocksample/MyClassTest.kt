package com.audhil.medium.gweatherapp.basic_mockito_samples.mocksample

import com.audhil.medium.gweatherapp.util.reifiedMock
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import java.lang.IllegalArgumentException

//  based on https://www.vogella.com/tutorials/Mockito/article.html
class MyClassTest {

    //  sample 1 with static mock function
    @Test
    fun testSample1() {
        val classMock = mock(MyClass::class.java)
        `when`(classMock.getUniqueId()).thenReturn(43)

        assertEquals(classMock.getUniqueId(), 43)
    }

    //  sample 2 return multiple values
    @Test
    fun testSample2() {
        val iterator = mock(Iterator::class.java)
        `when`(iterator.next()).thenReturn("Jack").thenReturn("and").thenReturn("jill")
        val result = iterator.next() as String + " " + iterator.next() as String + " " + iterator.next() as String

        assertEquals("Jack and jill", result)
    }

    //  sample 3 return values based on input
    @Test
    fun testSample3() {
        val comparable: Comparable<String> = reifiedMock()
        `when`(comparable.compareTo("Mockito")).thenReturn(1)
        `when`(comparable.compareTo("Jack and jill")).thenReturn(2)

        assertEquals(1, comparable.compareTo("Mockito"))
        assertEquals(2, comparable.compareTo("Jack and jill"))
    }

    //  sample 4 return values independent of the input value
    @Test
    fun testSample4() {
        val comparable: Comparable<Int> = reifiedMock()
        `when`(comparable.compareTo(anyInt())).thenReturn(-1)

        assertEquals(-1, comparable.compareTo(4))
    }

    //  sample 5 return values based on type of provider parameter - NOT WORKING CHECK IT LATER - says "must not be null"
//    @Test
//    fun testSample5() {
//        val comparable: Comparable<MyClass> = reifiedMock()
//        `when`(comparable.compareTo(isA(MyClass::class.java))).thenReturn(0)
//        assertEquals(0, comparable.compareTo(MyClass()))
//    }

    //  sample 6
    @Test
    fun testSample6() {
        val someClass = mock(MyClass::class.java)
        `when`(someClass.getSomeRecord("Andrroid")).thenThrow(IllegalArgumentException("jack and jill, IllegalArgumentException"))
        try {
            someClass.getSomeRecord("Andrroid")
//            fail("Andrroid is misspellled")   //  fails the test case
        } catch (e: IllegalArgumentException) {
            println("outch it got exception")
        }
    }
}