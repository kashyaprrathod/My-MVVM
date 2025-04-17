package com.kashyap.mvvm_3_0.di

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
            private set

        val appContext: Context
            get() = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("TAG", "onCreate: My Application ")
        FirebaseApp.initializeApp(this)
        instance = this
    }

    //Restart App
    fun restartApp() {
        CoroutineScope(Dispatchers.Main).launch {
//            val i = MainActivity.newIntent(applicationContext)
//            i.flags = FLAG_ACTIVITY_NEW_TASK
//            applicationContext.startActivity(i)
        }
    }
}