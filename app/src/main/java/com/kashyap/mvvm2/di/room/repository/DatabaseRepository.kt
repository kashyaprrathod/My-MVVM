package com.kashyap.mvvm2.di.room.repository

import com.kashyap.mvvm2.di.remote.retrofit.helper.Resource
import com.kashyap.mvvm2.di.room.user.User
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    fun addUser(user: User): Flow<Resource<Any>>

    fun getUser(): Flow<Resource<List<User>>>
}