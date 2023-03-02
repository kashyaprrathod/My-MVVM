package com.kashyap.rathod.di.remote

import com.kashyap.rathod.data.UserBean
import com.kashyap.rathod.di.remote.help.ApiCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ApiRepositoryImpl(private val apiInterface: ApiInterface) : ApiRepository {
    override fun login(data: Map<String, Any>, apiCallBack: ApiCallback<Response<UserBean>>) {
        apiCallBack.onLoading()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiInterface.loginAsync(data).await()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        if (response.code() == 200) {
                            apiCallBack.onSuccess(response)
                        } else {
                            apiCallBack.onFailed(response.body().toString())
                        }
                    } else {
//                        val errorMsg = ApiUtils.getAPIError(response.errorBody())
                        apiCallBack.onFailed("Retrofit Error")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    apiCallBack.onErrorThrow(e)
                }
            }
        }
    }
}