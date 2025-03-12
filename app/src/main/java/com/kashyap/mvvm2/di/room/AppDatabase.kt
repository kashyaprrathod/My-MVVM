package com.kashyap.mvvm2.di.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kashyap.mvvm2.data.DiConst
import com.kashyap.mvvm2.data.DiConst.Room.DB_VERSION
import com.kashyap.mvvm2.di.room.user.User
import com.kashyap.mvvm2.di.room.user.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "MVVM2"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}