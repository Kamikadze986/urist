package com.example.urist1.bd.users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usersData: UsersData)

    @Query("select * from Users where email=:email")
    suspend fun getByEmail(email: String): UsersData?
}