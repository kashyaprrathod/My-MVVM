package com.kashyap.mvvm_3_0.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kashyap.mvvm_3_0.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    fun addData(user: UserEntity)

    @Query("SELECT * FROM user")
    fun getAll(): List<UserEntity>
}