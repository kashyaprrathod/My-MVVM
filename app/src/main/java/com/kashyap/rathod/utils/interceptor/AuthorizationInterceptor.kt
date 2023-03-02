package com.kashyap.rathod.utils.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val mainResponse = chain.proceed(chain.request())
        if (mainResponse.code == 403 || mainResponse.code == 401) {
            if (!chain.request().url.toString().contains("login")) {
//                Prefs.remove(SyncStateContract.Constants.PrefsKeys.USER_DATA)
//                Prefs.remove(SyncStateContract.Constants.PrefsKeys.AUTH_DATA)
//                MyApplication.instance?.restartApp()
            }
        }
        return mainResponse
    }
}