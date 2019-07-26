package com.audhil.medium.gweatherapp.ui.listing

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.audhil.medium.gweatherapp.data.model.api.response.APIResponse
import com.audhil.medium.gweatherapp.ui.base.BaseViewModel

class ListingViewModel(application: Application) : BaseViewModel(application) {

    //  data
    private val foreCastLiveData = MutableLiveData<APIResponse?>().apply {
        appRepository.apiCallBack = {
            value = it
        }
    }

    fun getForeCastLiveData(): LiveData<APIResponse?> {
        return foreCastLiveData
    }

    fun makeNetworkCall(arrayOfCities: Array<String>) {
        arrayOfCities.forEach {
            compositeDisposable.add(
                appRepository.fetchFromServerByCity(it)
            )
        }
    }


    //  test scenarios
    //  1. given - listOfCities -> when - making an API call is triggered -> return 1 response
    //  2. given -> listOfItems -> when - tapping list item -> return switching to another activity
    //  3. given -> listOfHardCoded items -> when - opening the app -> return update the UI with list
}