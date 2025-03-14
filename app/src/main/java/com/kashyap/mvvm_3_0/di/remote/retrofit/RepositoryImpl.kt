package com.kashyap.mvvm_3_0.di.remote.retrofit

import com.kashyap.mvvm_3_0.di.remote.helper.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val apiInterface: ApiInterface) {

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