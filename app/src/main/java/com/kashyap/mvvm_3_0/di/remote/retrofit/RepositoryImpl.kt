package com.kashyap.mvvm_3_0.di.remote.retrofit

import com.kashyap.mvvm_3_0.data.user.UserBean
import com.kashyap.mvvm_3_0.di.remote.helper.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) {

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

    fun fetchUsersData(hqMap: HashMap<String, Any>): Flow<Resource<ApiResponse<ArrayList<UserBean>>>> = flow {
        emit(Resource.loading(null))
        try {
            val response = apiInterface.fetchPageData(hqMap)
            if (response.isSuccessful) {
                emit(Resource.success(response.body() ?: return@flow))
            } else {
                emit(Resource.warn(null, "error message"))
                response.errorBody().toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            //for error
            emit(Resource.error(null, "error message"))
        }
    }
}