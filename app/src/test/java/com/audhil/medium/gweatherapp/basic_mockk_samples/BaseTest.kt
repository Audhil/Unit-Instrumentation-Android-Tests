package com.audhil.medium.gweatherapp.basic_mockk_samples

import com.audhil.medium.gweatherapp.GDelegate
import com.audhil.medium.gweatherapp.data.remote.AppAPIs
import com.audhil.medium.gweatherapp.di.components.DaggerTestAppComponent
import com.audhil.medium.gweatherapp.di.modules.TestApplicationModule
import com.audhil.medium.gweatherapp.di.modules.TestRepositoryModule
import javax.inject.Inject

open class BaseTest {

    @Inject
    lateinit var appAPIs: AppAPIs

    fun setUp() {
        val component = DaggerTestAppComponent.builder()
            .applicationModule(TestApplicationModule(GDelegate()))
            .repositoryModule(TestRepositoryModule())
            .build()
        component.into(this)
    }
}