package com.kashyap.mvvm_3_0.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val uId: Int? = null,
    @ColumnInfo(name = "first_name") val firstName: String = "",
    @ColumnInfo(name = "last_name") val lastName: String = ""
)
