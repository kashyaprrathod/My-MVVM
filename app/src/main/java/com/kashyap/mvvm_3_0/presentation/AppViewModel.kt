package com.kashyap.mvvm_3_0.presentation

import androidx.lifecycle.ViewModel
import com.kashyap.mvvm_3_0.data.repository.DatabaseRepositoryImpl
import com.kashyap.mvvm_3_0.data.repository.FirebaseRepositoryImpl
import com.kashyap.mvvm_3_0.data.repository.RemoteRepositoryImpl
import javax.inject.Inject

open class AppViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var apiRepository: RemoteRepositoryImpl

    @Inject
    lateinit var firebaseRepository: FirebaseRepositoryImpl

    @Inject
    lateinit var databaseRepositoryImpl: DatabaseRepositoryImpl
}