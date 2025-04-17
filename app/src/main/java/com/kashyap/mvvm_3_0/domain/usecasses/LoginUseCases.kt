package com.kashyap.mvvm_3_0.domain.usecasses

import com.kashyap.mvvm_3_0.data.remote.Resource
import com.kashyap.mvvm_3_0.domain.model.UserBean
import com.kashyap.mvvm_3_0.domain.repositoiry.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCases @Inject constructor(val repository: RemoteRepository) {
    fun loginNow(): Flow<Resource<UserBean>> {
        return repository.getUsersData2()
    }
}