@file:Suppress("unused")

package com.kashyap.mvvm2.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.location.Geocoder
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.util.Patterns
import kotlin.math.roundToInt

object AppUtils {

    //Get android id
    @SuppressLint("HardwareIds")
    fun getAndroidId(context: Context): String {
        return try {
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        } catch (e: Exception) {
            ""
        }
    }

    //Get device id
    @SuppressLint("HardwareIds")
    fun getDeviceId(c: Context): String {
        return Settings.Secure.getString(c.contentResolver, Settings.Secure.ANDROID_ID)
    }

    //Convert dp to pixel
    fun convertDpToPixel(dp: Float): Int {
        val metrics = Resources.getSystem().displayMetrics
        val px = dp * (metrics.densityDpi / 160f)
        return px.roundToInt()
    }

    //Get address from latitude and longitude
    fun getAddress(latitude: String, longitude: String, context: Context): String {
        val geocoder = Geocoder(context)
        @Suppress("DEPRECATION") val addressList = geocoder.getFromLocation(latitude.toDouble(), longitude.toDouble(), 1)
        if (addressList?.size!! > 0) {
            val address = StringBuffer()
            val addressData = addressList[0]
            if (addressData.locality != null) {
                address.append(addressData.locality).append(", ")
            }
            address.append(addressData.adminArea)

            return address.toString()
        }
        return ""
    }

    //Check internet connection
    @Suppress("DEPRECATION")
    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    //Check email is valid or not
    fun emailInvalid(email: String?): Boolean {
        return if (email == null) true else !Patterns.EMAIL_ADDRESS.matcher(email.trim { it <= ' ' }).matches()
    }
}