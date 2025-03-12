package com.kashyap.mvvm2.utils.permissions

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

object PermissionsUtil {

    fun getDeniedPermissions(permissions: Array<String>, grantResults: IntArray): Array<String> =
        permissions.filterIndexed { index, _ ->
            grantResults[index] == PackageManager.PERMISSION_DENIED
        }.toTypedArray()

    fun getPermanentlyDeniedPermissions(fragment: Fragment, permissions: Array<String>, grantResults: IntArray): Array<String> =
        permissions.filterIndexed { index, s ->
            grantResults[index] == PackageManager.PERMISSION_DENIED && !fragment.shouldShowRequestPermissionRationale(s)
        }.toTypedArray()

    fun hasSelfPermission(activity: Context?, permissions: Array<String>): Boolean {
        activity?.let {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }

        return true
    }
}