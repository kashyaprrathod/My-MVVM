package com.kashyap.mvvm2.di.room.repository

import com.kashyap.mvvm2.di.remote.retrofit.helper.Resource
import com.kashyap.mvvm2.di.room.AppDatabase
import com.kashyap.mvvm2.di.room.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class DatabaseRepositoryImpl(private val appDatabase: AppDatabase) : DatabaseRepository {

    override fun addUser(user: User): Flow<Resource<Any>> = callbackFlow {
        try {
            trySend(Resource.loading(null))
            appDatabase.userDao().insertAll(user)
            trySend(Resource.success(""))
        } catch (e: Exception) {
            e.printStackTrace()
            trySend(Resource.error(null, e.message.orEmpty()))
        }
    }

    override fun getUser(): Flow<Resource<List<User>>> = callbackFlow {
        try {
            trySend(Resource.loading(null))
            appDatabase.userDao().getAll().collect {
                trySend(Resource.success(it))
            }
        } catch (e: Exception) {
            trySend(Resource.error(null, e.message.orEmpty()))
        }
    }
}