package com.kashyap.mvvm_3_0.di.remote.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("sample")
    suspend fun fetchApi(): Response<Any>
}