package com.kashyap.rathod.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kashyap.rathod.data.room.user.User
import com.kashyap.rathod.data.room.user.UserDao

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}