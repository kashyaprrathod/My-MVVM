package com.kashyap.mvvm2.utils.permissions

import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_DENIED
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri.fromParts
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.kashyap.mvvm2.R

class PermissionCheckerFragment : Fragment() {

    private var quickPermissionsRequest: QuickPermissionsRequest? = null

    interface QuickPermissionsCallback {
        fun shouldShowRequestPermissionsRationale(quickPermissionsRequest: QuickPermissionsRequest?)
        fun onPermissionsGranted(quickPermissionsRequest: QuickPermissionsRequest?)
        fun onPermissionsPermanentlyDenied(quickPermissionsRequest: QuickPermissionsRequest?)
        fun onPermissionsDenied(quickPermissionsRequest: QuickPermissionsRequest?)
    }

    companion object {
        fun newInstance(): PermissionCheckerFragment = PermissionCheckerFragment()
    }

    private var mListener: QuickPermissionsCallback? = null

    fun setListener(listener: QuickPermissionsCallback) {
        mListener = listener
        Log.w("TAG", "onCreate: listeners set")
    }

    private fun removeListener() {
        mListener = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.w("TAG", "onCreate: permission fragment created")
    }

    fun setRequestPermissionsRequest(quickPermissionsRequest: QuickPermissionsRequest?) {
        this.quickPermissionsRequest = quickPermissionsRequest
    }

    private fun removeRequestPermissionsRequest() {
        quickPermissionsRequest = null
    }

    fun clean() {
        if (quickPermissionsRequest != null) {
            if ((quickPermissionsRequest?.deniedPermissions?.size ?: 0) > 0)
                mListener?.onPermissionsDenied(quickPermissionsRequest)

            removeRequestPermissionsRequest()
            removeListener()
        } else {
            Log.w("TAG", "clean: QuickPermissionsRequest has already completed its flow. No further callbacks will be called for the current flow.")
        }
    }

    fun requestPermissionsFromUser() {
        if (quickPermissionsRequest != null) {
            Log.w("TAG", "requestPermissionsFromUser: requesting permissions")
            requestPermissions.launch(quickPermissionsRequest?.permissions)
        } else {
            Log.w("TAG", "requestPermissionsFromUser: QuickPermissionsRequest has already completed its flow. Cannot request permissions again from the request received from the callback. You can start the new flow by calling runWithPermissions() { } again.")
        }
    }

    private val requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        Log.w("TAG", "passing callback")

        handlePermissionResult(
            permissions.map { it.key }.toTypedArray(),
            permissions.map { if (it.value) PERMISSION_GRANTED else PERMISSION_DENIED }.toCollection(ArrayList()).toIntArray()
        )
    }

    private fun handlePermissionResult(permissions: Array<String>, grantResults: IntArray) {
        if (permissions.isEmpty()) {
            Log.w("TAG", "handlePermissionResult: Permissions result discarded. You might have called multiple permissions request simultaneously")
            return
        }

        if (PermissionsUtil.hasSelfPermission(context, permissions)) {
            quickPermissionsRequest?.deniedPermissions = emptyArray()
            mListener?.onPermissionsGranted(quickPermissionsRequest)
            clean()
        } else {
            val deniedPermissions = PermissionsUtil.getDeniedPermissions(permissions, grantResults)
            quickPermissionsRequest?.deniedPermissions = deniedPermissions

            var shouldShowRationale = true
            var isPermanentlyDenied = false
            for (element in deniedPermissions) {
                val rationale = shouldShowRequestPermissionRationale(element)
                if (!rationale) {
                    shouldShowRationale = false
                    isPermanentlyDenied = true
                    break
                }
            }

            if (quickPermissionsRequest?.handlePermanentlyDenied == true && isPermanentlyDenied) {
                quickPermissionsRequest?.permanentDeniedMethod?.let {
                    quickPermissionsRequest?.permanentlyDeniedPermissions = PermissionsUtil.getPermanentlyDeniedPermissions(this, permissions, grantResults)
                    mListener?.onPermissionsPermanentlyDenied(quickPermissionsRequest)
                    return
                }

                val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                builder.setMessage(quickPermissionsRequest?.permanentlyDeniedMessage.orEmpty())
                    .setTitle(resources.getString(R.string.app_name))
                    .setPositiveButton("SETTINGS") { _, _ ->
                        openAppSettings()
                    }
                    .setNegativeButton("CANCEL") { _, _ ->
                        clean()
                    }
                    .setCancelable(false)
                    .show()
                return
            }

            if (quickPermissionsRequest?.handleRationale == true && shouldShowRationale) {

                quickPermissionsRequest?.rationaleMethod?.let {
                    mListener?.shouldShowRequestPermissionsRationale(quickPermissionsRequest)
                    return
                }

                val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                builder.setMessage(quickPermissionsRequest?.permanentlyDeniedMessage.orEmpty())
                    .setTitle(resources.getString(R.string.app_name))
                    .setPositiveButton("SETTINGS") { _, _ ->
                        requestPermissionsFromUser()
                    }
                    .setNegativeButton("CANCEL") { _, _ ->
                        clean()
                    }
                    .setCancelable(false)
                    .show()
                return
            }

            clean()
        }
    }

    fun openAppSettings() {
        if (quickPermissionsRequest != null) {
            val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS, fromParts("package", activity?.packageName, null))
            startActivityResult.launch(intent)
        } else {
            Log.w("TAG", "openAppSettings: QuickPermissionsRequest has already completed its flow. Cannot open app settings")
        }
    }

    private val startActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val permissions = quickPermissionsRequest?.permissions ?: emptyArray()
        val grantResults = IntArray(permissions.size)
        permissions.forEachIndexed { index, s ->
            grantResults[index] = context?.let { ActivityCompat.checkSelfPermission(it, s) } ?: PERMISSION_DENIED
        }

        handlePermissionResult(permissions, grantResults)
    }
}
