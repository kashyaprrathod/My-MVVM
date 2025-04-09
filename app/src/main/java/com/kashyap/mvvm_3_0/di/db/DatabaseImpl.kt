package com.kashyap.mvvm_3_0.di.db

import android.util.Log
import com.kashyap.mvvm_3_0.di.db.tables.UserTable
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DatabaseImpl @Inject constructor(private val database: DatabaseHelper) {

    fun addUser(user: UserTable) = flow<Any> {
        database.studentDao().addData(user)
    }

    fun getUser() = flow<Any> {
        val list = database.studentDao().getAll()
        Log.e("TAG", "getUser: $list")
    }
}