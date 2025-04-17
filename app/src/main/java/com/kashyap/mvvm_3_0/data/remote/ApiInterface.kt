package com.kashyap.mvvm_3_0.data.remote

import com.kashyap.mvvm_3_0.data.model.user.UserDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/users")
    fun getUserData(): Response<ApiResponse<UserDto>>
}