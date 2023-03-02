package com.kashyap.rathod.di.remote

import com.kashyap.rathod.data.EndPoints
import com.kashyap.rathod.data.UserBean
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @POST(EndPoints.Auth.login)
    fun loginAsync(@Body data: Map<String, @JvmSuppressWildcards Any>): Deferred<Response<UserBean>>
}