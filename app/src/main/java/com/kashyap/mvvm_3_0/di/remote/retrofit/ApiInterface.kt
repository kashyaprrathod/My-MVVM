package com.kashyap.mvvm_3_0.di.remote.retrofit

import com.kashyap.mvvm_3_0.data.user.UserBean
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {
    @GET("sample")
    suspend fun fetchApi(): Response<Any>

    @GET("api/users")
    suspend fun fetchPageData(@QueryMap map: HashMap<String, Any>): Response<ApiResponse<ArrayList<UserBean>>>
}