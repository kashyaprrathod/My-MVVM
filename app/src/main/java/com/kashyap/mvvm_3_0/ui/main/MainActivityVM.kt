package com.kashyap.mvvm_3_0.ui.main

import androidx.lifecycle.viewModelScope
import com.kashyap.mvvm_3_0.di.db.tables.UserTable
import com.kashyap.mvvm_3_0.di.ui.base.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor() : AppViewModel() {

    fun change() = viewModelScope.launch {
        apiRepository.sampleApiCall().collect {

        }
    }

    fun addDataToRoom() {
        databaseRepositoryImpl.addUser(UserTable(firstName = "User1", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User2", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User3", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User4", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User5", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User6", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User7", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User8", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User9", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User10", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User11", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User12", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User13", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User14", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User15", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User16", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User17", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User18", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User19", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User20", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User21", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User22", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User23", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User24", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User25", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User26", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User27", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User28", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User29", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User30", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User31", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User32", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User33", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User34", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User35", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User36", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User37", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User38", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User39", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User40", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User41", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User42", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User43", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User44", lastName = "LName"))
        databaseRepositoryImpl.addUser(UserTable(firstName = "User45", lastName = "LName"))
    }
}