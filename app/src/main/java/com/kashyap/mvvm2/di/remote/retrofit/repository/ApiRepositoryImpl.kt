package com.kashyap.mvvm2.di.remote.retrofit.repository

import com.kashyap.mvvm2.di.remote.retrofit.helper.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

class ApiRepositoryImpl(private var apiInterface: ApiInterface) {

    fun sampleApiCall(): Flow<Resource<Any>> = flow {
        emit(Resource.loading(null))
        try {
            val response = apiInterface.fetchApi()
            if (response.isSuccessful) {
                emit(Resource.success(""))
            } else {
                emit(Resource.warn("", "error message"))
                response.errorBody().toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            //for error
            emit(Resource.error("", "error message"))
        }
    }
}