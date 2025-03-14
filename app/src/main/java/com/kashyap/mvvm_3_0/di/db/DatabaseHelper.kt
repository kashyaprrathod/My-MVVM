package com.kashyap.mvvm_3_0.di.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kashyap.mvvm_3_0.data.DiConst.Room.DB_VERSION
import com.kashyap.mvvm_3_0.di.db.dao.StudentsDao
import com.kashyap.mvvm_3_0.di.db.tables.UserTable

@Database(entities = [UserTable::class], version = DB_VERSION, exportSchema = false)
abstract class DatabaseHelper : RoomDatabase() {
    abstract fun studentDao(): StudentsDao
}