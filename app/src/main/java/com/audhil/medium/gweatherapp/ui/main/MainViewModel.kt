package com.audhil.medium.gweatherapp.ui.main

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.audhil.medium.gweatherapp.data.model.api.NetworkError
import com.audhil.medium.gweatherapp.data.model.api.response.APIResponse
import com.audhil.medium.gweatherapp.ui.base.BaseViewModel

open class MainViewModel(application: Application) : BaseViewModel(application) {

    var failureLayoutVisibility = ObservableField<Boolean>(false)
    var loadingLayoutVisibility = ObservableField<Boolean>(false)

    //  error live data
    var errorLiveData =
        MediatorLiveData<NetworkError>().apply {
            addSource(appRepository.errorLiveData) {
                value = it
            }
        }

    //  data
    val foreCastLiveData = MutableLiveData<APIResponse?>().apply {
        appRepository.apiCallBack = {
            value = it
        }
    }

    //  api call
    open fun loadForecasts(latitude: String, longitude: String) = compositeDisposable.add(
        appRepository.fetchFromServer(latitude, longitude)
    )
}