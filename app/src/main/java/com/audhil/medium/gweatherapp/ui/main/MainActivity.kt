package com.audhil.medium.gweatherapp.ui.main

import android.Manifest
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.audhil.medium.gweatherapp.BR
import com.audhil.medium.gweatherapp.databinding.ActivityMainBinding
import com.audhil.medium.gweatherapp.ui.base.BaseLifeCycleActivity
import com.audhil.medium.gweatherapp.util.showVLog
import com.audhil.medium.gweatherapp.R
import com.audhil.medium.gweatherapp.data.model.api.NetworkError
import com.audhil.medium.gweatherapp.util.ConstantsUtil
import com.audhil.medium.gweatherapp.util.isNetworkConnected
import com.audhil.medium.gweatherapp.util.showToast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetBehavior
import android.content.DialogInterface
import android.content.Intent
import android.app.AlertDialog
import android.content.Context
import android.location.LocationManager
import com.audhil.medium.gweatherapp.GDelegate


class MainActivity : BaseLifeCycleActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override fun getBindingVariable(): Int = BR.main_view_model

    override fun getLayoutId(): Int = R.layout.activity_main

    private val mockUrl by lazy {
        intent?.extras?.getString(ConstantsUtil.MOCK_URL, null)
    }

    private var bSheetBehaviour: BottomSheetBehavior<LinearLayout>? = null

    private val recyclerViewAdapter by lazy {
        RAdapter()
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun internetAvailable() {
        showVLog("internet available")
    }

    override fun internetUnAvailable() {
        showVLog("internet not available")
    }

    override fun onRestart() {
        super.onRestart()
        doCheckPermission()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bSheetBehaviour = BottomSheetBehavior.from(viewDataBinding.bsheetLayoutContainer)
        bSheetBehaviour?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                bSheetBehaviour?.state = BottomSheetBehavior.STATE_EXPANDED
            }
        })
        doCheckPermission()
        initErrorObserver()
        initDataObserver()
    }

    private fun initErrorObserver() =
        viewModel.errorLiveData.observe(this, Observer { networkError ->
            when (networkError) {
                NetworkError.DISCONNECTED,
                NetworkError.BAD_URL,
                NetworkError.UNKNOWN,
                NetworkError.SOCKET_TIMEOUT -> {
                    showVLog("ERROR_LIVE_DATA DISCONNECTED BAD_URL UNKNOWN SOCKET_TIMEOUT")
                    hideLoadingScreen()
                    showRetryScreen()
                }

                else ->
                    Unit
            }
        })

    @SuppressLint("SetTextI18n")
    private fun initDataObserver() =
        viewModel.foreCastLiveData.observe(this, Observer {
            viewDataBinding.apply {
                it?.let {
                    hideLoadingScreen()
                    //  show current temp
                    it.current?.tempC?.let {
                        currentTempTxtView.visibility = View.VISIBLE
                        ValueAnimator.ofInt(1, it.toFloat().toInt()).apply {
                            duration = 2000
                            addUpdateListener {
                                currentTempTxtView.text = it.animatedValue.toString() + ConstantsUtil.DEGREE_CIRCLE
                            }
                            start()
                        }
                    }
                    //  show city name
                    cityTxtView.apply {
                        text = it.location?.name
                        visibility = View.VISIBLE
                    }
                    //  show bottom sheet
                    viewDataBinding.bsheetLayoutContainer.postDelayed({
                        viewDataBinding.recyclerView.apply {
                            adapter = recyclerViewAdapter
                        }
                        recyclerViewAdapter.addItems(it.forecast?.forecastdayArray?.toMutableList())
                        bSheetBehaviour?.state = BottomSheetBehavior.STATE_EXPANDED
                    }, 1000)
                } ?: run {
                    hideLoadingScreen()
                    showRetryScreen()
                }
            }
        })

    //  checks permission & proceed to app
    private fun doCheckPermission() {
        //  no need to handle runtime permission
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            proceedToApp()
            return
        }
        //  handle permission
        if (!isPermissionGrantedOrNot(Manifest.permission.ACCESS_FINE_LOCATION))
            askPermissionFromUser(
                Manifest.permission.ACCESS_FINE_LOCATION,
                ConstantsUtil.PERM_FINE_LOCATION_REQUEST_CODE
            )
        else if (!isPermissionGrantedOrNot(Manifest.permission.ACCESS_COARSE_LOCATION))
            askPermissionFromUser(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                ConstantsUtil.PERM_COARSE_LOCATION_REQUEST_CODE
            )
        else
            proceedToApp()
    }

    private val locationManager by lazy {
        getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    //  showing alert to turn on GPS
    private fun buildAlertMessageNoGps() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                startActivity(Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.cancel()
                finish()
            }
        val alert = builder.create()
        alert.show()
    }

    //  proceed to app
    @SuppressLint("MissingPermission")
    fun proceedToApp() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps()
            return
        }
        showLoadingScreen()
        //  from test case
        mockUrl?.let {
            viewModel.appRepository.fetchFromServer(it)
        } ?:
        //  normal flow
        run {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            //  get lat lng, fetch api response
            fusedLocationClient.lastLocation.addOnSuccessListener {
                if (!isNetworkConnected()) {
                    getString(R.string.no_internet).showToast()
                    return@addOnSuccessListener
                }
                if (it == null) {
                    "Something not okay, pls, wait!".showToast()
                    viewDataBinding.root.postDelayed({
                        viewModel.loadForecasts(it?.latitude.toString(), it?.longitude.toString())
                    }, 5000)
                } else
                    viewModel.loadForecasts(it.latitude.toString(), it.longitude.toString())
            }
        }
    }

    //  retry logic
    private fun showRetryScreen() {
        viewModel.failureLayoutVisibility.set(true)
        viewDataBinding.failureLayout.retryBtn.setOnClickListener {
            viewModel.failureLayoutVisibility.set(false)
            proceedToApp()
        }
    }

    //  loading logic
    //  rotate animation
    private val rotateAnimation by lazy {
        RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f)
    }

    //  showing loading screen with animation
    private fun showLoadingScreen() {
        viewModel.loadingLayoutVisibility.set(true)
        rotateAnimation.apply {
            duration = 1000
            interpolator = LinearInterpolator()
            repeatCount = Animation.INFINITE
        }
        viewDataBinding.loadingLayout.loadingImg.startAnimation(rotateAnimation)
    }

    private fun hideLoadingScreen() {
        rotateAnimation.cancel()
        viewModel.loadingLayoutVisibility.set(false)
    }

    override fun permissionResult(grantResults: IntArray, requestCode: Int) {
        super.permissionResult(grantResults, requestCode)
        when (requestCode) {
            ConstantsUtil.PERM_FINE_LOCATION_REQUEST_CODE ->
                when {
                    grantResults[0] == PackageManager.PERMISSION_GRANTED ->
                        if (!isPermissionGrantedOrNot(Manifest.permission.ACCESS_COARSE_LOCATION))
                            (R.string.perm_needed).showToast()
                        else
                            proceedToApp()
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) -> {
                        showPermissionDeniedMessage(getString(R.string.perm_needed))
                        finish()
                    }
                    else ->
                        showPermissionRationaleAlertDialog(
                            ConstantsUtil.EMPTY,
                            getString(R.string.perm_needed),
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) {
                            if (it.equals(ConstantsUtil.IAM_SURE, true))
                                finish()
                        }
                }

            ConstantsUtil.PERM_COARSE_LOCATION_REQUEST_CODE ->
                when {
                    grantResults[0] == PackageManager.PERMISSION_GRANTED ->
                        if (!isPermissionGrantedOrNot(Manifest.permission.ACCESS_FINE_LOCATION))
                            (R.string.perm_needed).showToast()
                        else
                            proceedToApp()
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) -> {
                        showPermissionDeniedMessage(getString(R.string.perm_needed))
                        finish()
                    }
                    else ->
                        showPermissionRationaleAlertDialog(
                            ConstantsUtil.EMPTY,
                            getString(R.string.perm_needed),
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) {
                            if (it.equals(ConstantsUtil.IAM_SURE, true))
                                finish()
                        }
                }
        }
    }
}