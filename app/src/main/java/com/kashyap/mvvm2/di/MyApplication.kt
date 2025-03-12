package com.kashyap.mvvm2.di

import android.app.Application
import android.content.ContextWrapper
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.firebase.FirebaseApp
import com.google.firebase.app
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.kashyap.mvvm2.data.DiConst
import com.kashyap.mvvm2.di.remote.retrofit.repository.ApiInterface
import com.kashyap.mvvm2.di.remote.retrofit.RetrofitClient
import com.kashyap.mvvm2.di.room.AppDatabase
import com.kashyap.mvvm2.di.room.DatabaseClient
import com.kashyap.mvvm2.utils.prefs.Prefs

class MyApplication : Application() {

    lateinit var apiInterface: ApiInterface
    lateinit var firebaseDatabase: FirebaseDatabase

    val roomDatabase: AppDatabase by lazy { AppDatabase.getDatabase(applicationContext) }
    val prefsDataStore: DataStore<Preferences> by preferencesDataStore(name = DiConst.Prefs.PREF_NAME)

    companion object {
        var instance: MyApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        FirebaseApp.initializeApp(this)

        apiInterface = RetrofitClient().getRetrofitClient().create(ApiInterface::class.java)
        firebaseDatabase = FirebaseDatabase.getInstance()
    }
}