package com.audhil.medium.gweatherapp.repository

import com.audhil.medium.gweatherapp.data.model.api.response.APIResponse
import com.audhil.medium.gweatherapp.rx.makeFlowableRxConnection
import com.audhil.medium.gweatherapp.util.CallBack
import com.audhil.medium.gweatherapp.util.ConstantsUtil
import io.reactivex.disposables.Disposable

class AppRepository : BaseRepository() {

    var apiCallBack: CallBack<APIResponse>? = null

    fun fetchFromServer(url: String) {
        appAPIs.getForecastTempWithUrl(url)
            .makeFlowableRxConnection(this, ConstantsUtil.TESTING)
    }

    fun fetchFromServer(latitude: String, longitude: String): Disposable =
        appAPIs.getForecastTemp(latlng = latitude + ConstantsUtil.COMMA + longitude)
            .makeFlowableRxConnection(this, ConstantsUtil.FORECAST_TEMP_API)

    override fun onSuccess(obj: Any?, tag: String) {
        when (tag) {
            ConstantsUtil.TESTING,
            ConstantsUtil.FORECAST_TEMP_API ->
                (obj as? APIResponse)?.let {
                    apiCallBack?.invoke(it)
                }

            else ->
                Unit
        }
    }

    //  chain network calls
    fun fetchFromServerByCity(cityName: String): Disposable =
        appAPIs.getForecastTempByCity(city = cityName)
            .makeFlowableRxConnection(this, ConstantsUtil.FORECAST_TEMP_API)

}