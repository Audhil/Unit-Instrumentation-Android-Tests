package com.audhil.medium.gweatherapp.ui.base

import android.content.*
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.audhil.medium.gweatherapp.R
import com.audhil.medium.gweatherapp.util.CallBack
import com.audhil.medium.gweatherapp.util.ConstantsUtil
import com.audhil.medium.gweatherapp.util.isNetworkConnected
import com.audhil.medium.gweatherapp.util.showToast

abstract class BaseActivity : AppCompatActivity() {

    //  internet callbacks
    abstract fun internetAvailable()

    abstract fun internetUnAvailable()

    //  bool
    var isInternetUnAvailableBool = false

    //  internet listener
    private var internetListener: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (this@BaseActivity.isNetworkConnected() && isInternetUnAvailableBool) {
                internetAvailable()
                isInternetUnAvailableBool = false
            } else {
                if (!this@BaseActivity.isNetworkConnected()) {
                    internetUnAvailable()
                    isInternetUnAvailableBool = true
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(internetListener, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(internetListener)
    }

    //  RUNTIME PERMISSIONS
    fun isPermissionGrantedOrNot(permission: String): Boolean =
        ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

    //  asking user permission
    fun askPermissionFromUser(permission: String, permissionRequestCode: Int) =
        ActivityCompat.requestPermissions(this, arrayOf(permission), permissionRequestCode)

    //  permission result
    open fun permissionResult(grantResults: IntArray, requestCode: Int) {
        //  implement in sub-classes
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (grantResults.isNotEmpty())
            when (requestCode) {
                ConstantsUtil.PERM_FINE_LOCATION_REQUEST_CODE -> {
                    permissionResult(grantResults, requestCode)
                }
            }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    //  permission denied snackbar or toast
    fun showPermissionDeniedMessage(msg: String) = msg.showToast()

    fun showPermissionRationaleAlertDialog(
        title: String,
        msg: String,
        permission: String,
        callBack: CallBack<String>? = null
    ) =
        AlertDialog.Builder(this@BaseActivity)
            .setMessage(msg)
            .setPositiveButton(getString(R.string.retry)) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null) // No I18N
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton(getString(R.string.iam_sure)) { dialog, which ->
                callBack?.invoke(ConstantsUtil.IAM_SURE)
            }
            .setCancelable(false)
            .show()
}