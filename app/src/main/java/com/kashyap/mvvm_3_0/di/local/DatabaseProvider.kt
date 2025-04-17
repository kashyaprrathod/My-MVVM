package com.kashyap.mvvm_3_0.di.local

import android.content.Context
import androidx.room.Room
import com.kashyap.mvvm_3_0.data.Constants
import com.kashyap.mvvm_3_0.data.local.DatabaseHelper
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
            Constants.Room.DB_NAME
        ).build()
    }
}