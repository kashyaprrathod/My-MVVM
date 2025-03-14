package com.kashyap.mvvm_3_0

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        var instance: MyApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("TAG", "onCreate: My Application ")
        FirebaseApp.initializeApp(this)
        instance = this
    }
}