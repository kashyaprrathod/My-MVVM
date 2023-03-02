@file:Suppress("unused")

package com.kashyap.rathod.data

import com.kashyap.rathod.data.EndPoints.URLs.v1

object EndPoints {

    object URLs {
        const val BaseUrl: String = "URL"
        const val v1 = "v1/"
    }

    object Auth {
        const val login: String = "${v1}login"
    }
}