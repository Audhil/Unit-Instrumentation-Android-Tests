package com.audhil.medium.gweatherapp

import android.app.Application
import com.audhil.medium.gweatherapp.di.components.ApplicationComponent
import com.audhil.medium.gweatherapp.di.components.DaggerApplicationComponent
import com.audhil.medium.gweatherapp.di.modules.ApplicationModule
import com.facebook.stetho.Stetho

open class GDelegate : Application() {

    open lateinit var appComponent: ApplicationComponent

    companion object {
        lateinit var INSTANCE: GDelegate
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = getApplicationComponent()
        appComponent.inject(this)
        Stetho.initializeWithDefaults(this)
    }

    open fun getApplicationComponent(): ApplicationComponent =
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
}