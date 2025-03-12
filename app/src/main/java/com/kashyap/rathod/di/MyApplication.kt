package com.kashyap.rathod.di

import android.app.Application
import androidx.room.Room
import com.kashyap.rathod.data.room.AppDatabase
import com.kashyap.rathod.di.remote.ApiInterface
import com.kashyap.rathod.di.remote.ApiRepositoryImpl
import com.kashyap.rathod.di.remote.RetrofitBuilder

class MyApplication : Application() {

    companion object {
        var instance: MyApplication? = null
            private set
    }

    var db: AppDatabase? = null
    var apiRepoImpl: ApiRepositoryImpl? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        //Room
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "mvvm").build()

        initWebServices()
    }

    //Init Web Services and Network handler
    private fun initWebServices() {
        val apiInt = RetrofitBuilder.getRetrofit().create(ApiInterface::class.java)
        apiRepoImpl = ApiRepositoryImpl(apiInt)
    }
}