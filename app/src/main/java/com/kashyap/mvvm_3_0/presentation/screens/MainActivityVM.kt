package com.kashyap.mvvm_3_0.presentation.screens

import androidx.lifecycle.viewModelScope
import com.kashyap.mvvm_3_0.data.local.entity.UserEntity
import com.kashyap.mvvm_3_0.presentation.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor() : AppViewModel() {

    fun change() = viewModelScope.launch {
        apiRepository.getUsersData2().collect {

        }
    }

    fun addDataToRoom() {
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User1", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User2", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User3", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User4", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User5", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User6", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User7", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User8", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User9", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User10", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User11", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User12", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User13", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User14", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User15", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User16", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User17", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User18", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User19", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User20", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User21", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User22", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User23", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User24", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User25", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User26", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User27", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User28", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User29", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User30", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User31", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User32", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User33", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User34", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User35", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User36", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User37", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User38", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User39", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User40", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User41", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User42", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User43", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User44", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserEntity(firstName = "User45", lastName = "LName"))
    }
}