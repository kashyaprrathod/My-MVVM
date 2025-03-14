package com.kashyap.mvvm_3_0.di.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kashyap.mvvm_3_0.di.db.tables.UserTable
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentsDao {

    @Insert
    fun addData(user: UserTable)

    @Query("SELECT * FROM user")
    fun getAll(): List<UserTable>
}