package com.kashyap.mvvm_3_0.di.remote.firebase

import com.google.firebase.database.FirebaseDatabase
import com.kashyap.mvvm_3_0.di.remote.helper.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(val firebaseDatabase: FirebaseDatabase) {

    fun setFireBaseData(location: String, value: Any): Flow<Resource<Any>> = callbackFlow {
        firebaseDatabase.getReference(location).setValue(value).addOnCompleteListener {
            trySend(Resource.success(""))
        }.addOnFailureListener {
            trySend(Resource.error(null, it.message.orEmpty()))
        }
    }
}