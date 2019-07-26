package com.audhil.medium.gweatherapp.data.remote

import com.audhil.medium.gweatherapp.data.model.api.response.APIResponse
import com.audhil.medium.gweatherapp.util.ConstantsUtil
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface AppAPIs {

    @GET(
        "{apiVersion}" + ConstantsUtil.FORWARD_SLASH + ConstantsUtil.FORECAST_TEMP_API + ConstantsUtil.FORWARD_SLASH
    )
    fun getForecastTemp(
        @Path(value = "apiVersion", encoded = true)
        apiVersion: String = ConstantsUtil.API_VERSION,
        @Query(ConstantsUtil.KEY_PARAM)
        apiKey: String = ConstantsUtil.API_KEY,
        @Query(ConstantsUtil.QUERY_PARAM)
        latlng: String? = null,
        @Query(ConstantsUtil.DAYS_PARAM)
        days: String = ConstantsUtil.FOUR
    ): Flowable<APIResponse>

    @GET(
        "{apiVersion}" + ConstantsUtil.FORWARD_SLASH + ConstantsUtil.FORECAST_TEMP_API + ConstantsUtil.FORWARD_SLASH
    )
    fun getForecastTempByCity(
        @Path(value = "apiVersion", encoded = true)
        apiVersion: String = ConstantsUtil.API_VERSION,
        @Query(ConstantsUtil.KEY_PARAM)
        apiKey: String = ConstantsUtil.API_KEY,
        @Query(ConstantsUtil.QUERY_PARAM)
        city: String? = null,
        @Query(ConstantsUtil.DAYS_PARAM)
        days: String = ConstantsUtil.FOUR
    ): Flowable<APIResponse>

    //  for testing purpose
    @GET
    fun getForecastTempWithUrl(
        @Url
        url: String
    ): Flowable<APIResponse>
}