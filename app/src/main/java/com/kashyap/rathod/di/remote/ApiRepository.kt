package com.kashyap.rathod.di.remote

import com.kashyap.rathod.data.UserBean
import com.kashyap.rathod.di.remote.help.ApiCallback
import retrofit2.Response

interface ApiRepository {
    fun login(data: Map<String, Any>, apiCallBack: ApiCallback<Response<UserBean>>)
}