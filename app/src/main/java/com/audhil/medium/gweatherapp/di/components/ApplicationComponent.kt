package com.audhil.medium.gweatherapp.di.components

import com.audhil.medium.gweatherapp.GDelegate
import com.audhil.medium.gweatherapp.di.modules.ApplicationModule
import com.audhil.medium.gweatherapp.di.modules.RepositoryModule
import com.audhil.medium.gweatherapp.repository.BaseRepository
import com.audhil.medium.gweatherapp.ui.base.BaseViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (ApplicationModule::class),
        (RepositoryModule::class)
    ]
)
interface ApplicationComponent {
    fun inject(into: GDelegate)
    fun inject(into: BaseRepository)
    fun inject(into: BaseViewModel)
}