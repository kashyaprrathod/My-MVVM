package com.kashyap.mvvm_3_0.di.remote.retrofit

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kashyap.mvvm_3_0.BuildConfig
import com.kashyap.mvvm_3_0.data.Constants.Prefs.USER_DATA
import com.kashyap.mvvm_3_0.data.remote.EndPoints
import com.kashyap.mvvm_3_0.data.remote.ApiInterface
import com.kashyap.mvvm_3_0.data.repository.RemoteRepositoryImpl
import com.kashyap.mvvm_3_0.domain.model.UserBean
import com.kashyap.mvvm_3_0.domain.repositoiry.RemoteRepository
import com.kashyap.mvvm_3_0.utils.prefs.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitProvider {

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
    fun provideUserBean(): UserBean {
        return try {
            Gson().fromJson(Prefs.getString(USER_DATA, ""), object : TypeToken<UserBean>() {}.type)
        } catch (_: Exception) {
            UserBean("")
        }
    }


    /*interface InterceptorQualifiers {
        @Qualifier
        annotation class Authorization

        @Qualifier
        annotation class Request
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
            source.request(Long.MAX_VALUE) // Buffer the entire body.
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
    }*/


    interface OkHttpQualifiers {
        @Qualifier
        annotation class BASIC

        @Qualifier
        annotation class RETROFIT
    }

    @Provides
    @Singleton
    @OkHttpQualifiers.BASIC
    fun providesHttpClientBasic(): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .dispatcher(dispatcher)
            .build()
    }

    @Provides
    @Singleton
    @OkHttpQualifiers.RETROFIT
    fun providesHttpClientRetrofit(
        @OkHttpQualifiers.BASIC okHttpClient: OkHttpClient,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authorization: AuthorizationInterceptor,
        request: RequestInterceptor
    ): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        return okHttpClient.newBuilder()
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
    fun providesRetrofitClient(@OkHttpQualifiers.RETROFIT okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(EndPoints.URLs.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.Companion())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(apiInterface: ApiInterface): RemoteRepository {
        return RemoteRepositoryImpl(apiInterface)
    }
}