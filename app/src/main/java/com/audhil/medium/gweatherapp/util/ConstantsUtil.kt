package com.audhil.medium.gweatherapp.util

import java.text.SimpleDateFormat
import java.util.*

object ConstantsUtil {
    const val DASH = "---"
    const val HYPHEN = "-"
    const val EMPTY = ""
    const val INVALID_INPUT = "invalid input"
    const val TESTING = "Testing"

    const val IAM_SURE = "IAM_SURE"

    const val DEGREE_CIRCLE = "\u00B0"

    const val BLANK_SPACE = " "
    const val PERM_FINE_LOCATION_REQUEST_CODE = 9090
    const val PERM_COARSE_LOCATION_REQUEST_CODE = 8787

    const val COMMA = ","

    const val IS_LAUNCHED_FROM_TEST = "isLaunchedFromTest"

    const val FORWARD_SLASH = "/"
    const val API_ENDPOINT = "https://api.apixu.com"

    const val FORECAST_TEMP_API = "forecast.json"

    const val API_VERSION = "v1"

    const val KEY_PARAM = "key"
    const val QUERY_PARAM = "q"
    const val DAYS_PARAM = "days"

    const val API_KEY = "41c23902be8e47c0a1d171804190206"

    const val MOCK_URL = "MockURL"
    const val FOUR = "4"

    private val dateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    }

    private val dayFormat by lazy {
        SimpleDateFormat("EEEE", Locale.getDefault())
    }

    //  input string -> 2019-06-04
    fun findDayOfDate(dateString: String): String =
        if (!dateString.contains(HYPHEN))
            EMPTY
        else
            dayFormat.format(dateFormat.parse(dateString))
}