package com.kashyap.mvvm_3_0.di.remote.retrofit

import android.util.Log
import com.kashyap.mvvm_3_0.domain.model.UserBean
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.Long
import java.nio.charset.Charset
import javax.inject.Inject
import kotlin.Exception
import kotlin.String

class RequestInterceptor @Inject constructor(val userBean: UserBean?) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("Content-Type", "application/json")
        builder.addHeader("Accept", "application/json")
        try {
            builder.addHeader("Authorization", "Bearer ${userBean?.authToken}")
        } catch (e: Exception) {
            Log.e("Authorization Exception", "")
        }

        val response = chain.proceed(builder.build())

        //Use for response log
        val respString = getResponseString(response)
        //Log.i("respString", "intercept: $respString")

        return response
    }

    private fun getResponseString(response: Response): String {
        val responseBody = response.body
        val source = responseBody?.source()
        source?.request(Long.MAX_VALUE) // Buffer the entire body.
        val buffer = source?.buffer
        var charset: Charset? = Charset.forName("UTF-8")
        val contentType = responseBody?.contentType()
        if (contentType != null) {
            charset = contentType.charset(Charset.forName("UTF-8"))
        }
        if (charset == null) {
            return ""
        }
        return buffer?.clone()?.readString(charset).orEmpty()
    }
}