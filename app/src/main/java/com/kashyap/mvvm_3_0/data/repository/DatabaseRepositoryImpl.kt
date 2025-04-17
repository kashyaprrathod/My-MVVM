package com.kashyap.mvvm_3_0.data.repository

import android.util.Log
import com.kashyap.mvvm_3_0.data.local.DatabaseHelper
import com.kashyap.mvvm_3_0.data.local.entity.UserEntity
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(private val database: DatabaseHelper) {

    fun addUser(user: UserEntity) = flow<Any> {
        database.userDao().addData(user)
    }

    fun getUser() = flow<Any> {
        val list = database.userDao().getAll()
        Log.e("TAG", "getUser: $list")
    }
}