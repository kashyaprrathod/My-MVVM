package com.kashyap.mvvm_3_0.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kashyap.mvvm_3_0.data.Constants
import com.kashyap.mvvm_3_0.data.local.dao.UserDao
import com.kashyap.mvvm_3_0.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = Constants.Room.DB_VERSION, exportSchema = false)
abstract class DatabaseHelper : RoomDatabase() {
    abstract fun userDao(): UserDao
}