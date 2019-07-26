package com.audhil.medium.gweatherapp.di.modules

import android.content.Context
import android.content.pm.PackageManager
import com.audhil.medium.gweatherapp.GDelegate
import com.audhil.medium.gweatherapp.data.remote.AppAPIs
import com.audhil.medium.gweatherapp.repository.AppRepository
import com.audhil.medium.gweatherapp.util.ConstantsUtil
import com.audhil.medium.gweatherapp.util.GLog
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
open class ApplicationModule(private val application: GDelegate) {

    @Provides
    @Singleton
    fun giveContext(): Context = this.application

    @Provides
    @Singleton
    fun givePackageManager(): PackageManager = application.packageManager

    @Provides
    @Singleton
    open fun giveLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            GLog.v("APP LOGG", message)
        })
        interceptor.level = if (GLog.DEBUG_BOOL)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    //  okHttpClient
    @Provides
    open fun giveOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
//                for testing
//            .addInterceptor { chain ->
//                Response.Builder()
//                    .code(400)
//                    .message("{}")
//                    .request(chain.request())
//                    .protocol(Protocol.HTTP_1_0)
//                    .body(ResponseBody.create(MediaType.parse("application/json"), "{}".toByteArray()))
//                    .build()
//            }
            //  increasing time outs
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @Provides
    fun giveRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(ConstantsUtil.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @Provides
    open fun giveRetrofitService(okHttpClient: OkHttpClient): AppAPIs =
        Retrofit.Builder()
            .baseUrl(ConstantsUtil.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AppAPIs::class.java)

    @Provides
    @Named("test")
    open fun giveAppAPIs(): AppAPIs =
        Retrofit.Builder().build().create(AppAPIs::class.java)

    @Provides
    @Singleton
    fun giveGSONInstance(): Gson = Gson()
}

@Module
open class RepositoryModule {
    @Provides
    open fun giveGPRepo(): AppRepository = AppRepository()
}