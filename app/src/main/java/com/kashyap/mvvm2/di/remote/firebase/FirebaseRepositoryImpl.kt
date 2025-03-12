package com.kashyap.mvvm2.di.remote.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kashyap.mvvm2.di.remote.retrofit.helper.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseRepositoryImpl(private val firebaseDatabase: FirebaseDatabase) : FirebaseRepository {

    override fun setFireBaseData(location: String, value: Any): Flow<Resource<Any>> = callbackFlow {
        firebaseDatabase.getReference(location).setValue(value).addOnCompleteListener {
            trySend(Resource.success(""))
        }.addOnFailureListener {
            trySend(Resource.error(null, it.message.orEmpty()))
        }
    }

    override fun getFireBaseData(location: String): Flow<Resource<Any>> = callbackFlow {
        firebaseDatabase.getReference(location).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                trySend(Resource.success(""))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.error(null, error.message))
            }
        })
    }
}