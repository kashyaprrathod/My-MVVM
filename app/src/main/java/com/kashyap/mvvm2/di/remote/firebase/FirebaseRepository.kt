package com.kashyap.mvvm2.di.remote.firebase

import com.kashyap.mvvm2.di.remote.retrofit.helper.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {

    fun setFireBaseData(location: String, value: Any): Flow<Resource<Any>>

    fun getFireBaseData(location: String): Flow<Resource<Any>>
}