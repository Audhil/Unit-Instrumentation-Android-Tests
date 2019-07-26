package com.audhil.medium.gweatherapp

import com.audhil.medium.gweatherapp.di.TestApplicationModule
import com.audhil.medium.gweatherapp.di.components.ApplicationComponent
import com.audhil.medium.gweatherapp.di.components.DaggerApplicationComponent

open class UiGDelegate : GDelegate() {

    override fun getApplicationComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .applicationModule(TestApplicationModule(this))
            .build()
    }
}