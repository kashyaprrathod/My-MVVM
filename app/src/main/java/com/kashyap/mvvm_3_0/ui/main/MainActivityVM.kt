package com.kashyap.mvvm_3_0.ui.main

import androidx.lifecycle.viewModelScope
import com.kashyap.mvvm_3_0.di.db.DatabaseImpl
import com.kashyap.mvvm_3_0.di.remote.firebase.FirebaseRepositoryImpl
import com.kashyap.mvvm_3_0.di.remote.retrofit.RepositoryImpl
import com.kashyap.mvvm_3_0.di.ui.base.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor() : AppViewModel() {

    fun change() = viewModelScope.launch {
        apiRepository.sampleApiCall().collect {

        }
    }

    fun addDataToFirebase() = viewModelScope.launch(Dispatchers.IO) {
        databaseRepositoryImpl.addUser().collect {}

        delay(1000)

        databaseRepositoryImpl.getUser().collect {

        }
    }
}