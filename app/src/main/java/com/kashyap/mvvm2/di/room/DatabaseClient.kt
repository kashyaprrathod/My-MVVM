package com.kashyap.mvvm2.di.room

import android.content.Context
import androidx.room.Room
import com.kashyap.mvvm2.data.DiConst.Room.DB_NAME

class DatabaseClient {

    fun getDatabaseClient(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }
}