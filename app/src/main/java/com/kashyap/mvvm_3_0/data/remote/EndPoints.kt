package com.kashyap.mvvm_3_0.data.remote

import com.kashyap.mvvm_3_0.BuildConfig

object EndPoints {

    object URLs {
        const val BASE_URL: String = BuildConfig.BASE_URL
        const val v1 = "v1/"
    }

    object Auth {
        const val login: String = "${URLs.v1}login"
    }
}