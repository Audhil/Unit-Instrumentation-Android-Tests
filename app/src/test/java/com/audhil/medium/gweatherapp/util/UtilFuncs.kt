package com.audhil.medium.gweatherapp.util

import org.mockito.ArgumentCaptor
import org.mockito.Mockito.mock

inline fun <reified T : Any> reifiedMock(): T = mock(T::class.java)

//  Returns ArgumentCaptor.capture() as nullable type to avoid java.lang.IllegalStateException when null is returned
//  from: https://github.com/googlesamples/android-architecture-components/blob/master/BasicRxJavaSampleKotlin/app/src/test/java/com/example/android/observability/MockitoKotlinHelpers.kt
fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()