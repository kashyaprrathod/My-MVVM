package com.kashyap.mvvm2.di.app

import android.view.View
import androidx.lifecycle.ViewModel
import com.kashyap.mvvm2.di.MyApplication
import com.kashyap.mvvm2.di.datastore.preference.PrefsRepository
import com.kashyap.mvvm2.di.datastore.preference.PrefsRepositoryImpl
import com.kashyap.mvvm2.di.remote.firebase.FirebaseRepository
import com.kashyap.mvvm2.di.remote.firebase.FirebaseRepositoryImpl
import com.kashyap.mvvm2.di.remote.retrofit.helper.SingleLiveEvent
import com.kashyap.mvvm2.di.remote.retrofit.repository.ApiRepositoryImpl
import com.kashyap.mvvm2.di.room.repository.DatabaseRepository
import com.kashyap.mvvm2.di.room.repository.DatabaseRepositoryImpl

open class AppViewModel : ViewModel() {

    val apiRepository: ApiRepositoryImpl = ApiRepositoryImpl(MyApplication.instance?.apiInterface!!)

    val firebaseRepository: FirebaseRepository = FirebaseRepositoryImpl(MyApplication.instance?.firebaseDatabase!!)

    val databaseRepository: DatabaseRepository = DatabaseRepositoryImpl(MyApplication.instance?.roomDatabase!!)

    //    val databaseRepository: DatabaseRepository? = null
    val prefsDataStoreRepository: PrefsRepository = PrefsRepositoryImpl(MyApplication.instance?.prefsDataStore!!)

    @JvmField
    val obrClick: SingleLiveEvent<View> = SingleLiveEvent()
}