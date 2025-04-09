package com.kashyap.mvvm_3_0.di.remote.retrofit

import com.google.gson.annotations.SerializedName

open class ApiResponse<Any>(
    @SerializedName("data")
    var data: Any? = null,

    @SerializedName("message")
    val message: String = "",

    @SerializedName("page")
    val page: Int = 0,

    @SerializedName("total_pages")
    val totalPages: Int = 0,
)