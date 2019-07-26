package com.audhil.medium.gweatherapp.basic_mockk_samples

import com.audhil.medium.gweatherapp.GDelegate
import com.audhil.medium.gweatherapp.data.model.api.response.APIResponse
import com.audhil.medium.gweatherapp.data.remote.AppAPIs
import com.audhil.medium.gweatherapp.di.components.DaggerTestAppComponent
import com.audhil.medium.gweatherapp.di.modules.TestApplicationModule
import com.audhil.medium.gweatherapp.di.modules.TestRepositoryModule
import io.mockk.every
import io.reactivex.Flowable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

class AppRepositoryInjectNamedTest {

    @Inject
    @Named("test")
    lateinit var appAPIs: AppAPIs

//    @field:[Inject Named("test")]
//    lateinit var appAPIs: AppAPIs

    @Before
    fun setUp() {
        val component = DaggerTestAppComponent.builder()
            .applicationModule(TestApplicationModule(GDelegate()))
            .repositoryModule(TestRepositoryModule())
            .build()
        component.into(this)
    }

    @Test
    fun `my test`() {
        assertNotNull(appAPIs)
        every { appAPIs.getForecastTempWithUrl("r") } returns Flowable.just(APIResponse())
        val result = appAPIs.getForecastTempWithUrl("r")
        result.test()
            .assertValue(APIResponse())
    }
}