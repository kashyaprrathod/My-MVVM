package com.kashyap.mvvm2.di.datastore.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.kashyap.mvvm2.data.DiConst.Prefs.USER_DATA
import com.kashyap.mvvm2.data.UserBean
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PrefsRepositoryImpl(private val dataStore: DataStore<Preferences>) : PrefsRepository {

    private val userDataKey = stringPreferencesKey(USER_DATA)
    fun insertUserData(userData: UserBean) = callbackFlow<String> {
        dataStore.edit {
            it[userDataKey] = Gson().toJson(userData)
        }
    }

    override fun getUserData(): Flow<UserBean> = flow {
        dataStore.data.map {
            it[userDataKey]
        }.collect {
            emit(Gson().fromJson(it, UserBean::class.java))
        }
    }
}