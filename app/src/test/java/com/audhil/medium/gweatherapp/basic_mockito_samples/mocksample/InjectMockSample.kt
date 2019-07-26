package com.audhil.medium.gweatherapp.basic_mockito_samples.mocksample

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

//  https://howtodoinjava.com/mockito/mockito-mock-injectmocks/
@RunWith(MockitoJUnitRunner::class)
class InjectMockSample {

    @InjectMocks
    var mainClass: MainClass? = null

    @Mock
    var dependentClassOne: DatabaseDAO? = null

    @Mock
    var dependentClassTwo: NetworkDAO? = null

    @Test
    fun validateTest() {
        val saved = mainClass?.save("temp.txt")
        assertEquals(true, saved)

        //  just verification
        verify(dependentClassOne, times(1))?.save("temp.txt")
        verify(dependentClassTwo, times(1))?.save("temp.txt")
    }
}

open class NetworkDAO {
    open fun save(fileName: String) {
        println("Saved in network location")
    }
}

open class DatabaseDAO {
    open fun save(fileName: String) {
        println("Saved in database")
    }
}

open class MainClass {

    var database: DatabaseDAO? = null
    var network: NetworkDAO? = null

    //Setters and getters

    fun save(fileName: String): Boolean {
        database!!.save(fileName)
        println("Saved in database in Main class")

        network!!.save(fileName)
        println("Saved in network in Main class")

        return true
    }
}