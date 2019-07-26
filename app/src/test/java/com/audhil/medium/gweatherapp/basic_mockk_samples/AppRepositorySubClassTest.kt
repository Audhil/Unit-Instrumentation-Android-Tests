package com.audhil.medium.gweatherapp.basic_mockk_samples

import com.audhil.medium.gweatherapp.data.model.api.response.APIResponse
import io.mockk.every
import io.reactivex.Flowable
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AppRepositorySubClassTest : BaseTest() {

    @Before
    fun setUpTest() = super.setUp()

    @Test
    fun `my test`() {
        Assert.assertNotNull(appAPIs)
        every { appAPIs.getForecastTempWithUrl("r") } returns Flowable.just(APIResponse())
        val result = appAPIs.getForecastTempWithUrl("r")
        result
            .test()
            .assertValue(APIResponse())
    }
}