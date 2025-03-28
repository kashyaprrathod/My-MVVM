package com.kashyap.mvvm_3_0.di.ui.base

import android.view.View
import androidx.lifecycle.ViewModel
import com.kashyap.mvvm2.di.remote.retrofit.helper.SingleLiveEvent
import com.kashyap.mvvm_3_0.di.db.DatabaseImpl
import com.kashyap.mvvm_3_0.di.remote.firebase.FirebaseRepositoryImpl
import com.kashyap.mvvm_3_0.di.remote.retrofit.RepositoryImpl
import javax.inject.Inject

open class AppViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var apiRepository: RepositoryImpl


    @Inject
    lateinit var firebaseRepository: FirebaseRepositoryImpl

    @Inject
    lateinit var databaseRepositoryImpl: DatabaseImpl

    @JvmField
    val obrClick: SingleLiveEvent<View> = SingleLiveEvent()
}