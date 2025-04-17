package com.kashyap.mvvm_3_0.di.remote.retrofit

import com.kashyap.mvvm_3_0.di.MyApplication
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import javax.inject.Inject


class AuthorizationInterceptor @Inject constructor(@RetrofitProvider.OkHttpQualifiers.BASIC val okHttpClient: OkHttpClient) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val mainResponse = chain.proceed(chain.request())
        if (mainResponse.code == 401) {
            okHttpClient.dispatcher.cancelAll()
            MyApplication.instance.restartApp()
        }
        return mainResponse
    }
}