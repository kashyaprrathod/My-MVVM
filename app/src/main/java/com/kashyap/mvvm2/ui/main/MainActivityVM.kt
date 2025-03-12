package com.kashyap.mvvm2.ui.main

import androidx.lifecycle.viewModelScope
import com.kashyap.mvvm2.di.app.AppViewModel
import com.kashyap.mvvm2.di.remote.retrofit.helper.Resource
import com.kashyap.mvvm2.di.remote.retrofit.helper.SingleLiveEvent
import com.kashyap.mvvm2.di.room.user.User
import com.kashyap.mvvm2.data.UserBean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityVM : AppViewModel() {

    val obrSample = SingleLiveEvent<Resource<Any>>()


    fun fetchSampleApi() = viewModelScope.launch {
        apiRepository.sampleApiCall().collect {
            obrSample.value = it
        }
    }

    fun setFireBaseData() {
    }

    val obrFirebaseData = SingleLiveEvent<Resource<Any>>()

    fun getFirebaseData() = CoroutineScope(Dispatchers.Main).launch {
        firebaseRepository.getFireBaseData("").collect {
            obrFirebaseData.value = it
        }
    }

    fun setUserData(user: User) {
        databaseRepository.addUser(user)
    }

    fun getUserData(): Flow<UserBean> {
        return prefsDataStoreRepository.getUserData()
    }
}