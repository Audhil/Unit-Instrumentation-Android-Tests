package com.audhil.medium.gweatherapp.repository

import com.audhil.medium.gweatherapp.GDelegate
import com.audhil.medium.gweatherapp.data.model.api.AppExecutors
import com.audhil.medium.gweatherapp.data.model.api.ErrorLiveData
import com.audhil.medium.gweatherapp.data.model.api.NetworkError
import com.audhil.medium.gweatherapp.data.remote.AppAPIs
import com.audhil.medium.gweatherapp.rx.IRxListeners
import com.audhil.medium.gweatherapp.util.showELog
import com.audhil.medium.gweatherapp.util.showVLog
import javax.inject.Inject

abstract class BaseRepository : IRxListeners<Any> {

    @Inject
    lateinit var appAPIs: AppAPIs
    @Inject
    lateinit var errorLiveData: ErrorLiveData

    @Inject
    lateinit var appExecutors: AppExecutors

    init {
        GDelegate.INSTANCE.appComponent.inject(this)
    }

    override fun onSuccess(obj: Any?, tag: String) = showVLog("onSuccess() :: $tag")

    override fun onSocketTimeOutException(t: Throwable?, tag: String) {
        showELog("onSocketTimeOutException :: + tag :" + tag + " :: t?.message :: " + t?.message)
        errorLiveData.setNetworkError(NetworkError.SOCKET_TIMEOUT)
    }

    override fun onUnknownHostException(t: Throwable?, tag: String) {
        showELog("onUnknownHostException :: + tag :" + tag + " :: t?.message :: " + t?.message)
        errorLiveData.setNetworkError(NetworkError.DISCONNECTED)
    }

    override fun onIllegalArgumentException(t: Throwable?, tag: String) {
        showELog("onIllegalArgumentException :: + tag :" + tag + " :: t?.message :: " + t?.message)
        errorLiveData.setNetworkError(NetworkError.BAD_URL)
    }

    override fun onUnKnownException(t: Throwable?, tag: String) {
        showELog("onUnKnownException :: + tag :" + tag + " :: t?.message :: " + t?.message)
        errorLiveData.setNetworkError(NetworkError.UNKNOWN)
    }

    override fun onComplete(tag: String) {
        showELog("onComplete() :: tag :$tag")
    }
}