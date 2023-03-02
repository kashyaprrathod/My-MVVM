package com.kashyap.rathod.di.remote

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kashyap.rathod.utils.interceptor.AuthorizationInterceptor
import com.kashyap.rathod.utils.interceptor.RequestInterceptor
import com.kashyap.rathod.BuildConfig
import com.kashyap.rathod.data.EndPoints
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private val httpLoggingInterceptor =
        HttpLoggingInterceptor { message -> Log.e("Retrofit", message) }
            .setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )

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

    private val okHttpClient = getOkHttpClient()

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(EndPoints.URLs.BaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build() //Doesn't require the adapter
    }

    //val apiService: ApiInterface = getRetrofit().create(ApiInterface::class.java)
}