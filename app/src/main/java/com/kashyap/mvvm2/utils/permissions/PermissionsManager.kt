package com.kashyap.mvvm2.utils.permissions

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

private const val TAG = "runWithPermissions"


fun Context?.runWithPermissions(vararg permissions: String, options: QuickPermissionsOptions = QuickPermissionsOptions(), callback: () -> Unit): Any? {
    return runWithPermissionsHandler(this, permissions, callback, options)
}

fun Fragment?.runWithPermissions(vararg permissions: String, options: QuickPermissionsOptions = QuickPermissionsOptions(), callback: () -> Unit): Any? {
    return runWithPermissionsHandler(this, permissions, callback, options)
}

private fun runWithPermissionsHandler(target: Any?, permissions: Array<out String>, callback: () -> Unit, options: QuickPermissionsOptions): Nothing? {
    Log.d(TAG, "runWithPermissions: start")
    Log.d(TAG, "runWithPermissions: permissions to check: $permissions")

    if (target is AppCompatActivity || target is Fragment) {
        Log.d(TAG, "runWithPermissions: context found")

        val context = when (target) {
            is Context -> target
            is Fragment -> target.context
            else -> null
        }

        if (PermissionsUtil.hasSelfPermission(context, arrayOf(*permissions))) {
            Log.d(TAG, "runWithPermissions: already has required permissions. Proceed with the execution.")
            callback()
        } else {
            Log.d(TAG, "runWithPermissions: doesn't have required permissions")

            var permissionCheckerFragment = when (context) {
                is AppCompatActivity -> {
                    context.supportFragmentManager.findFragmentByTag(PermissionCheckerFragment::class.java.canonicalName) as PermissionCheckerFragment?
                }

                is Fragment -> {
                    context.childFragmentManager.findFragmentByTag(PermissionCheckerFragment::class.java.canonicalName) as PermissionCheckerFragment?
                }

                else -> {
                    null
                }
            }

            if (permissionCheckerFragment == null) {
                Log.d(TAG, "runWithPermissions: adding headless fragment for asking permissions")

                permissionCheckerFragment = PermissionCheckerFragment.newInstance()
                when (context) {
                    is AppCompatActivity -> {
                        context.supportFragmentManager.beginTransaction().apply {
                            add(permissionCheckerFragment, PermissionCheckerFragment::class.java.canonicalName)
                            commit()
                        }
                        context.supportFragmentManager.executePendingTransactions()
                    }

                    is Fragment -> {
                        context.childFragmentManager.beginTransaction().apply {
                            add(permissionCheckerFragment, PermissionCheckerFragment::class.java.canonicalName)
                            commit()
                        }
                        context.childFragmentManager.executePendingTransactions()
                    }
                }
            }

            permissionCheckerFragment.setListener(object : PermissionCheckerFragment.QuickPermissionsCallback {
                override fun onPermissionsGranted(quickPermissionsRequest: QuickPermissionsRequest?) {
                    Log.d(TAG, "runWithPermissions: got permissions")

                    try {
                        callback()
                    } catch (throwable: Throwable) {
                        throwable.printStackTrace()
                    }
                }

                override fun onPermissionsDenied(quickPermissionsRequest: QuickPermissionsRequest?) {
                    quickPermissionsRequest?.permissionsDeniedMethod?.invoke(quickPermissionsRequest)
                }

                override fun shouldShowRequestPermissionsRationale(quickPermissionsRequest: QuickPermissionsRequest?) {
                    quickPermissionsRequest?.rationaleMethod?.invoke(quickPermissionsRequest)
                }

                override fun onPermissionsPermanentlyDenied(quickPermissionsRequest: QuickPermissionsRequest?) {
                    quickPermissionsRequest?.permanentDeniedMethod?.invoke(quickPermissionsRequest)
                }
            })

            val permissionRequest = QuickPermissionsRequest(permissionCheckerFragment, arrayOf(*permissions))
            permissionRequest.handleRationale = options.handleRationale
            permissionRequest.handlePermanentlyDenied = options.handlePermanentlyDenied
            permissionRequest.rationaleMessage = options.rationaleMessage.ifBlank { "These permissions are required to perform this feature. Please allow us to use this feature. " }
            permissionRequest.permanentlyDeniedMessage = options.permanentlyDeniedMessage.ifBlank { "Some permissions are permanently denied which are required to perform this operation. Please open app settings to grant these permissions." }
            permissionRequest.rationaleMethod = options.rationaleMethod
            permissionRequest.permanentDeniedMethod = options.permanentDeniedMethod
            permissionRequest.permissionsDeniedMethod = options.permissionsDeniedMethod

            permissionCheckerFragment.setRequestPermissionsRequest(permissionRequest)
            permissionCheckerFragment.requestPermissionsFromUser()
        }
    } else {
        throw IllegalStateException("Found " + target!!::class.java.canonicalName + " : No support from any classes other than AppCompatActivity/Fragment")
    }
    return null
}