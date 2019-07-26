package com.audhil.medium.gweatherapp.di.components

import com.audhil.medium.gweatherapp.di.modules.ApplicationModule
import com.audhil.medium.gweatherapp.di.modules.RepositoryModule
import com.audhil.medium.gweatherapp.basic_mockk_samples.AppRepositoryInjectNamedTest
import com.audhil.medium.gweatherapp.basic_mockk_samples.AppRepositoryInjectTest
import com.audhil.medium.gweatherapp.basic_mockk_samples.BaseTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (ApplicationModule::class),
        (RepositoryModule::class)
    ]
)
interface TestAppComponent : ApplicationComponent {
    fun into(appRepositoryTest: AppRepositoryInjectNamedTest)
    fun into(appRepositoryTest: AppRepositoryInjectTest)
    fun into(baseTest: BaseTest)
}