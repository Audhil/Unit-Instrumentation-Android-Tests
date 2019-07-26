package com.audhil.medium.gweatherapp.basic_mockito_samples.mocksample

import com.audhil.medium.gweatherapp.util.capture
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsCollectionContaining.hasItem
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.verify
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArgumentCaptureSample {

    @Captor
    lateinit var captor: ArgumentCaptor<MutableList<String>>

    @Spy
    var mockList: MutableList<String> = mutableListOf()

    @Test
    fun `test Argument Capture`() {
        val normalList = mutableListOf("first", "second", "third", "fourth")
//        val mockList: MutableList<String> = reifiedMock()
        mockList.addAll(normalList)
        println("sizeOfList is : ${mockList.size}") //  0 when reifiedMock(), 4 when @Spy
        verify(mockList).addAll(capture(captor))
        val capturedArgs = captor.value
        assertThat(capturedArgs, hasItem("third"))
    }
}