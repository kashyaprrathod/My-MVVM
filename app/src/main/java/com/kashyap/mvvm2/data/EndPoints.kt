@file:Suppress("unused")

package com.kashyap.mvvm2.data

import com.kashyap.mvvm2.BuildConfig
import com.kashyap.mvvm2.data.EndPoints.URLs.v1

object EndPoints {

    object URLs {
        const val BASE_URL: String = BuildConfig.BASE_URL
        const val v1 = "v1/"
    }

    object Auth {
        const val login: String = "${v1}login"
    }
}