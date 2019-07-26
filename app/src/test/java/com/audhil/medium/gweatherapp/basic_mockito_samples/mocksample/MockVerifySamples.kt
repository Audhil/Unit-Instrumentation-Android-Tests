package com.audhil.medium.gweatherapp.basic_mockito_samples.mocksample

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

//  based on https://www.vogella.com/tutorials/Mockito/article.html
//  option 3 - to init mocks
@RunWith(MockitoJUnitRunner::class)
class MockVerifySamples {

    @Mock
    lateinit var myClass: MyClass

//    option 1 - to init mocks
//    @Rule
//    @JvmField
//    var rule: MockitoRule = MockitoJUnit.rule()

//    option 2 - to init mocks
//    @Before
//    fun setUp() {
//        MockitoAnnotations.initMocks(this)
//    }

    @Test
    fun `test Mock verify`() {
        `when`(myClass.getUniqueId()).thenReturn(88)

        myClass.getUniqueId()
        myClass.getUniqueId()

        // was the method called twice
        verify(myClass, times(2)).getUniqueId()
        verify(myClass, never()).testing(ArgumentMatchers.anyInt())

        // now check if method testing was called with the parameter "pimpi" or anyString()
        myClass.getSomeRecord("pimpi")
        verify(myClass).getSomeRecord(ArgumentMatchers.anyString())

        // now check if method testing was called with the parameter 3" or anyInt()
        myClass.testing(3)
        verify(myClass).testing(ArgumentMatchers.anyInt())

        verify(myClass, atLeastOnce()).testing(ArgumentMatchers.anyInt())

        myClass.getSomeRecord("pimpi")
        verify(myClass, atLeast(2)).getSomeRecord(ArgumentMatchers.anyString())

        myClass.getSomeRecord("pimpi")
        verify(myClass, atLeast(3)).getSomeRecord(ArgumentMatchers.anyString())
        myClass.getSomeRecord("pimpi")

//        uncomment below line to check - atMost usage
//        myClass.getSomeRecord("pimpi")
        verify(myClass, atMost(4)).getSomeRecord(ArgumentMatchers.anyString())
//        uncomment below line - still atMost will work
//        myClass.getSomeRecord("pimpi")

        // This let's you check that no other methods where called on this object.
        // You call it after you have verified the expected method calls.
        verifyNoMoreInteractions(myClass)
    }

//    for meaningful message during test failures
//    @After
//    fun validate() {
//        validateMockitoUsage()
//    }
}