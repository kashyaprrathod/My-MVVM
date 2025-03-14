package com.kashyap.mvvm_3_0.di.hilt

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kashyap.mvvm_3_0.BuildConfig
import com.kashyap.mvvm_3_0.data.EndPoints
import com.kashyap.mvvm_3_0.di.remote.retrofit.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitProvider {

    interface InterceptorQualifiers {
        @Qualifier
        annotation class Authorization

        @Qualifier
        annotation class Request
    }

    @Provides
    @Singleton
    fun providesLoggingInterceptors(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> Log.e("Retrofit", message) }
            .setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )
    }

    @Provides
    @Singleton
    @InterceptorQualifiers.Authorization
    fun providesAuthorizationInterceptors(): Interceptor {
        return Interceptor { chain ->
            val mainResponse = chain.proceed(chain.request())
            if (mainResponse.code == 403 || mainResponse.code == 401) {
                if (!chain.request().url.toString().contains("login")) {

                }
            }
            mainResponse
        }
    }

    @Provides
    @Singleton
    @InterceptorQualifiers.Request
    fun providesRequestInterceptors(): Interceptor {
        fun getResponseString(response: Response): String {
            val responseBody = response.body
            val source = responseBody.source()
            source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer
            var charset: Charset? = Charset.forName("UTF-8")
            val contentType = responseBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(Charset.forName("UTF-8"))
            }
            if (charset == null) {
                return ""
            }
            return buffer.clone().readString(charset)
        }

        return Interceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()
            builder.addHeader("Content-Type", "application/json")
            builder.addHeader("Accept", "application/json")
            try {
                builder.addHeader(
                    "Authorization",
                    "Bearer auth_token_here"
                )
            } catch (e: Exception) {
                Log.e("Authorization Exception", "")
            }

            val response = chain.proceed(builder.build())

            //Use for response log
            val respString = getResponseString(response)
            //Log.i("respString", "intercept: $respString")

            response
        }
    }

    @Provides
    @Singleton
    fun providesHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @InterceptorQualifiers.Authorization authorization: Interceptor,
        @InterceptorQualifiers.Request request: Interceptor
    ): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authorization)
            .addInterceptor(request)
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .dispatcher(dispatcher)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(EndPoints.URLs.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}
