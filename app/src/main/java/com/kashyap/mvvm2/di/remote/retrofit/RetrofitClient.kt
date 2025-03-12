package com.kashyap.mvvm2.di.remote.retrofit

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kashyap.mvvm2.BuildConfig
import com.kashyap.mvvm2.data.EndPoints
import com.kashyap.mvvm2.di.remote.retrofit.interceptor.RequestInterceptor
import com.kashyap.mvvm2.di.remote.retrofit.interceptor.AuthorizationInterceptor
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Log.e("Retrofit", message) }
        .setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )

    private val okHttpClient = getOkHttpClient()

    private fun getOkHttpClient(): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(AuthorizationInterceptor())
            .addInterceptor(RequestInterceptor())
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .dispatcher(dispatcher)
            .build()
    }

    fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder().baseUrl(EndPoints.URLs.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}