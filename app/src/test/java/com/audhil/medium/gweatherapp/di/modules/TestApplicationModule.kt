package com.audhil.medium.gweatherapp.di.modules

import com.audhil.medium.gweatherapp.GDelegate
import com.audhil.medium.gweatherapp.data.remote.AppAPIs
import com.audhil.medium.gweatherapp.repository.AppRepository
import io.mockk.mockk
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class TestApplicationModule(delegate: GDelegate) : ApplicationModule(delegate) {
    override fun giveAppAPIs(): AppAPIs = mockk()

    override fun giveRetrofitService(okHttpClient: OkHttpClient): AppAPIs = mockk()

    override fun giveOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient = mockk()

    override fun giveLoggingInterceptor(): HttpLoggingInterceptor = mockk()
}

class TestRepositoryModule : RepositoryModule() {
    override fun giveGPRepo(): AppRepository = mockk()
}