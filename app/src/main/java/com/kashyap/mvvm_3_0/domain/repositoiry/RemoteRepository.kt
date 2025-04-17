package com.kashyap.mvvm_3_0.domain.repositoiry

import com.kashyap.mvvm_3_0.data.remote.Resource
import com.kashyap.mvvm_3_0.domain.model.UserBean
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    fun getUsersData2(): Flow<Resource<UserBean>>
}