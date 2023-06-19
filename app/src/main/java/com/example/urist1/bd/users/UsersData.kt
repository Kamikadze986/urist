package com.example.urist1.bd.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Users")
data class UsersData(
    @PrimaryKey
    val email: String,
    val password: String
)
