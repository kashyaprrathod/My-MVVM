package com.kashyap.mvvm2.di.datastore.preference

import com.kashyap.mvvm2.data.UserBean
import kotlinx.coroutines.flow.Flow


interface PrefsRepository {

    fun getUserData(): Flow<UserBean>
}