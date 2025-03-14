package com.kashyap.mvvm_3_0.di.hilt

import android.content.Context
import androidx.room.Room
import com.kashyap.mvvm_3_0.data.DiConst.Room.DB_NAME
import com.kashyap.mvvm_3_0.di.db.DatabaseHelper
import com.kashyap.mvvm_3_0.di.db.DatabaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseProvider {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DatabaseHelper {
        return Room.databaseBuilder(
            context,
            DatabaseHelper::class.java,
            DB_NAME
        ).build()
    }
}