package com.kashyap.mvvm_3_0.di.hilt

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.kashyap.mvvm_3_0.di.remote.firebase.FirebaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseProvider {

    @Provides
    @Singleton
    fun provideFirebase(): FirebaseDatabase {
        Log.e("TAG", "provideFirebase ")
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseRepositoryImpl(firebase: FirebaseDatabase): FirebaseRepositoryImpl {
        Log.e("TAG", "provideFirebaseRepositoryImpl ")
        return FirebaseRepositoryImpl(firebase)
    }
}