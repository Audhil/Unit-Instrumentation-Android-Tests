package com.audhil.medium.gweatherapp.basic_mockito_samples.mocksample

import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.quality.Strictness

@RunWith(MockitoJUnitRunner::class)
class StrictStubRulesSample {

    //  without strict stub rules
    @Mock
    lateinit var deepThoughtClass: DeepThoughtClass

    //    this is normal test case - it'll fail for any mis-use of stubbing
    @Test
    fun `without strict stubs test`() {
        `when`(deepThoughtClass.getAnswerFor("jack and jill")).thenReturn("yup!")
        assertEquals("yup!", deepThoughtClass.getAnswerFor("jack and jill"))
        verify(deepThoughtClass, times(1)).getAnswerFor("jack and jill")
    }

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Test
    fun `with strict stub test`() {
        `when`(deepThoughtClass.getAnswerFor("jack and jill")).thenReturn("yup!")

        // this fails now with an UnnecessaryStubbingException since it is never called in the test
//        `when`(deepThoughtClass.otherMethod("I'm other method")).thenReturn(null)

        // this will now throw a PotentialStubbingProblem Exception since
        // we usually don't want to call methods on mocks without configured behavior
//        deepThoughtClass.someMethod()

        assertEquals("yup!", deepThoughtClass.getAnswerFor("jack and jill"))

        // verifyNoMoreInteractions now automatically verifies that all stubbed methods have been called as well
        verifyNoMoreInteractions(deepThoughtClass)
    }
}

//  some class
open class DeepThoughtClass {

    open fun getAnswerFor(thought: String): String {
        return thought
    }

    open fun otherMethod(thought: String): String {
        return thought
    }

    open fun someMethod() {
    }
}