package com.kashyap.mvvm2.di.remote.retrofit.repository

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("sample")
    suspend fun fetchApi(): Response<Any>
}