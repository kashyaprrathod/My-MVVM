@file:Suppress("unused", "DEPRECATION")

package com.kashyap.mvvm2.utils.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority


object LocationProvider {

    //For single location
    fun singleShotLocation(context: Context, callback: CustomLocationCallback) {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        val locationRequest = LocationRequest.create().apply {
            interval = 100
            fastestInterval = 50
            priority = Priority.PRIORITY_HIGH_ACCURACY
            maxWaitTime = 100
        }

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val mLocationCallback: LocationCallback =
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    callback.onNewLocationAvailable(
                        GPSCoordinates(
                            locationResult.locations[0].latitude,
                            locationResult.locations[0].longitude
                        )
                    )
                    fusedLocationProviderClient.removeLocationUpdates(this)
                }
            }

        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            mLocationCallback,
            Looper.myLooper() ?: return
        )
    }

    //For live location
    fun liveLocation(context: Context, callback: CustomLocationCallback) {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        val locationRequest = LocationRequest.create().apply {
            interval = 30000
            fastestInterval = 30000
            priority = Priority.PRIORITY_HIGH_ACCURACY
        }

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val mLocationCallback: LocationCallback =
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    for (location in locationResult.locations) {
                        callback.onNewLocationAvailable(
                            GPSCoordinates(
                                location.latitude,
                                location.longitude
                            )
                        )
                    }
                }
            }

        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            mLocationCallback,
            Looper.myLooper()!!
        )
    }

    //Interface and helper
    interface CustomLocationCallback {
        fun onNewLocationAvailable(location: GPSCoordinates?)
    }

    class GPSCoordinates {
        private var longitude = -1f
        private var latitude = -1f

        constructor(theLatitude: Float, theLongitude: Float) {
            longitude = theLongitude
            latitude = theLatitude
        }

        constructor(theLatitude: Double, theLongitude: Double) {
            longitude = theLongitude.toFloat()
            latitude = theLatitude.toFloat()
        }
    }
}